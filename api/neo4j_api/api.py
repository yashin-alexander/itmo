import json
from flask import Response, request
from neo4jrestclient.client import GraphDatabase


from . import constants


class Neo4jAPI:
    def __init__(self):
        self.db = GraphDatabase("http://localhost:7474/db/data/",
                                username=constants.USERNAME, password=constants.PASSWORD)

    @staticmethod
    def to_json(data):
        return json.dumps(data) + "\n"

    @property
    def request_parameters(self):
        return request.args.to_dict()

    def response(self, status_code, data):
        json_data = json.dumps(data, indent=4, sort_keys=True, default=str)
        return Response(
            status=status_code,
            mimetype="application/json",
            response=json_data
        )

    # CREATE methods
    def create_history(self):
        person_node = self.db.nodes.create(name=request.json['name'], id=request.json['id'])
        person_node.labels.add(constants.PERID)
        self._assign_history_node(person_node)
        return self.response(200, {})

    def _assign_history_node(self, person_node):
        for j in range(len(request.json['records'])):
            history_node = self.db.nodes.create()
            history_node.labels.add(constants.HISID)

            history_node.relationships.create("WHO", person_node)

            self._assign_date_node(history_node, request.json['records'][j])
            self._assign_company_node(history_node, request.json['records'][j]['company'])
            self._assign_action_node(history_node, request.json['records'][j]['action'])

    def _assign_date_node(self, history_node, data):
        date_node = self.db.nodes.create(
            date="{} {}".format(data['date'], data['time']))
        history_node.relationships.create("WHEN", date_node)
        date_node.labels.add(constants.DATE)

    def _assign_action_node(self, history_node, action):
        action_node = self.db.nodes.create(action=action)
        history_node.relationships.create("WHAT", action_node)
        action_node.labels.add(constants.ACTION)

    def _assign_company_node(self, history_node, company):
        company_node = self.db.nodes.create(company=company)
        history_node.relationships.create("WHERE", company_node)
        company_node.labels.add(constants.COMPANY)

    # READ methods
    def history(self):
        data = self.request_parameters
        id = data['id']
        query = 'Match (a)-[r1]-(b)-[r2]-(c) where a.id= ' + id + ' return a, type(r1), id(b), type(r2), c'
        raw_data = self.db.query(query, data_contents=True)
        return self.response(200, (raw_data.rows))

    # UPDATE methods
    def update_history_name(self):
        data = self.request_parameters
        id = data['id']
        name = data['name']
        query = 'MATCH(p: perid {id: ' + id + ' }) SET p.name = "' + name + '" RETURN p'
        self.db.query(query, data_contents=True)
        return self.response(200, {})

    # DELETE methods
    def delete_history(self):
        data = self.request_parameters
        id = data['id']
        query = 'MATCH (n:perid {id: ' + id + '}) DETACH DELETE n'
        self.db.query(query)
        return self.response(200, {})

import sys
import faker
import random

from neo4jrestclient.client import GraphDatabase

import constants


class Neo4jFiller:
    def __init__(self):
        self.faker = faker.Faker()

        self.db = GraphDatabase("http://localhost:7474/db/data/",
                                username=constants.USERNAME, password=constants.PASSWORD)
        self.perid_max = 0

    def create_person_nodes(self, nodes_number):
        for i in range(nodes_number):
            person_node = self.db.nodes.create(name=self.faker.name(), id=self.perid_max + i)
            person_node.labels.add(constants.PERID)
            self.assign_history_node(person_node)

    def assign_history_node(self, person_node):
        for j in range(random.randint(1, 3)):
            history_node = self.db.nodes.create()
            history_node.labels.add(constants.HISID)

            history_node.relationships.create("WHO", person_node)

            self.assign_date_node(history_node)
            self.assign_action_node(history_node)
            self.assign_company_node(history_node)

    def assign_date_node(self, history_node):
        date_node = self.db.nodes.create(date="{} {}".format(self.faker.date(), self.faker.time()))
        history_node.relationships.create("WHEN", date_node)
        date_node.labels.add(constants.DATE)

    def assign_action_node(self, history_node):
        action_node = self.db.nodes.create(action=self.faker.catch_phrase())
        history_node.relationships.create("WHAT", action_node)
        action_node.labels.add(constants.ACTION)

    def assign_company_node(self, history_node):
        company_node = self.db.nodes.create(company=self.faker.company())
        history_node.relationships.create("WHERE", company_node)
        company_node.labels.add(constants.COMPANY)

    def drop_db(self):
        query = 'MATCH (n) DETACH DELETE n'
        self.db.query(query)


if __name__ == '__main__':
    filler = Neo4jFiller()
    try:
        sys.argv[1]
        filler.drop_db()
    except IndexError:
        filler.create_person_nodes(1000)



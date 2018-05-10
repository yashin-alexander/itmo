from cassandra.cluster import Cluster

from . import constants


class CassandraAPI:
    def __init__(self):
        self.cluster = Cluster()
        # self.session = self.cluster.connect(constants.KEYSPACE_NAME)

    #     return json.dumps(data) + "\n"
    #
    # @property
    # def request_parameters(self):
    #     conditions = request.args.to_dict()
    #     if 'age' in conditions:
    #         conditions['age'] = int(conditions['age'])
    #     if 'rating' in conditions:
    #         conditions['rating'] = int(conditions['rating'])
    #
    #     return conditions
    #
    # def response(self, status_code, data):
    #     return Response(
    #         status=status_code,
    #         mimetype="application/json",
    #         response=self.to_json(data)
    #     )

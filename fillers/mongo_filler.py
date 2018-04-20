import faker
import random
import pprint
import pymongo


class MongoFiller:
    def __init__(self):
        self.faker = faker.Faker()

        self.client = pymongo.MongoClient()
        self.db = self.client['concierge']
        self.users = self.db['users']
        self.restaurants = self.db['restaurants']

    def get_collections_list(self):
        return self.db.collection_names()

    def get_users_data(self):
        pp = pprint.PrettyPrinter(indent=1)
        cursor = self.users.find()
        for document in cursor:
            pp.pprint(document)

    def get_restaurants_data(self):
        pp = pprint.PrettyPrinter(indent=1)
        cursor = self.restaurants.find()
        for document in cursor:
            pp.pprint(document)

    def create_restaurants_record(self):
        address = self.faker.address()
        name = self.faker.company()
        phone = self.faker.phone_number()
        rating = random.randint(1, 10)

        self.restaurants.insert_one({"address": address,
                                     'name': name,
                                     'phone': phone,
                                     'rating': rating})

    def create_restaurants_records(self, records_number):
        for _ in range(records_number):
            self.create_restaurants_record()


if __name__ == "__main__":
    filler = MongoFiller()
    print(filler.get_collections_list())
    filler.get_users_data()
    filler.get_restaurants_data()

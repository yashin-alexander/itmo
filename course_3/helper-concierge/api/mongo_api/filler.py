import faker
import random
import pymongo
import multiprocessing

import constants


class FillerFakes(faker.providers.BaseProvider):
    def __init__(self, *args, **kwargs):
        super(FillerFakes, self)
        self.fake = faker.Faker()

    def place(self):
        address = self.fake.address()
        name = self.fake.company()
        phone = self.fake.phone_number()
        rating = random.randint(1, 10)

        return {'address': address, 'name': name, 'phone': phone, 'rating': rating}

    def user(self):
        last_name = self.fake.last_name()
        return {
            'name': "{} {}".format(self.fake.first_name(), last_name),
            'age': random.randint(16, 66),
            'family': self.family(last_name),
            'profession': self.fake.job(),
            # 'reservations': self.reservations(), #
            # 'ordering_food': self.ordering_food(),
            'alarm_clock': self.alarm_clock(),
            'timetable': self.timetable(),
        }

    def family(self, last_name):
        family = {
            'spouse': self.spouse(),
            'children': self.children(last_name),
            'mother': self.mother(),
            'father': self.father()
        }
        return family

    def spouse(self):
        spouse = {}
        if random.randint(0, 1):
            spouse = {
                'name': self.fake.name(),
                'status': random.choice(constants.STATUSES_LIST),
                'relationships': random.choice(constants.RELATIONSHIPS_LIST)
            }
        return spouse

    def child(self, last_name):
        child = {
            'name': '{} {}'.format(self.fake.first_name(), last_name),
            'age': random.randint(0, 18),
            'relationships': random.choice(constants.RELATIONSHIPS_LIST)
        }
        return child

    def children(self, last_name):

        chilren = []
        children_count = random.randint(0, 3)
        for _ in range(children_count):
            chilren.append(self.child(last_name))

        return chilren

    def mother(self):
        mother = {
            'name': self.fake.name_female(),
            'relationships': random.choice(constants.RELATIONSHIPS_LIST)
        }
        return mother

    def father(self):
        father = {
            'name': self.fake.name_male(),
            'relationships': random.choice(constants.RELATIONSHIPS_LIST)
        }
        return father

    def reservations(self):
        reservations = []
        for _ in range(random.randint(0, 3)):
            reservations.append({
                'place': self.place(),
                'time': self.fake.time(),
                'number_of_persons': random.randint(1, 3)
            })
        return reservations

    def ordering_food(self):
        food = []
        for _ in range(random.randint(0, 3)):
            food.append({
                'place': self.place(),
                'time': self.fake.time(),
                'address': self.fake.address(),
                'dish': random.choice(constants.FOOD_LIST)
            })
        return food

    def alarm_clock(self):
        clocks = []
        for _ in range(random.randint(1, 3)):
            clocks.append(self.fake.date_time())
        return clocks

    def timetable(self):
        timetable = []
        for _ in range(random.randint(0, 2)):
            timetable.append({
                'time': self.fake.time(),
                'action': random.choice(constants.ACTIONS_LIST),
                'importance': random.randint(1, 10)
            })
        return timetable


class MongoFiller:
    def __init__(self):
        self.faker = faker.Faker()
        self.faker.add_provider(FillerFakes)

        self.client = pymongo.MongoClient('127.0.0.1:27001')
        self.db = self.client[constants.DB_NAME]
        self.users = self.db[constants.USERS_COLLECTION_NAME]
        self.places = self.db[constants.PLACES_COLLECTION_NAME]

    def create_places(self, records_number):
        for _ in range(records_number):
            self.create_place_record()

    def create_place_record(self):
        data = self.faker.place()
        self.places.insert_one(data)

    def create_users(self, record_number):
        for _ in range(record_number):
            self.create_user_record()

    def create_user_record(self):
        data = self.faker.user()
        data = self._add_referenced_fields(data)
        self.users.insert_one(data)

    def _add_referenced_fields(self, data):
        data.update({'reservations': []})
        data.update({'ordering_food': []})
        for i in range(random.randint(0, 3)):
            data['reservations'].append(self.get_random_existing_place())
        for i in range(random.randint(0, 3)):
            data['ordering_food'].append(self.get_random_existing_place())
        return data

    def get_random_existing_place(self):
        random_record = list(self.places.aggregate([{"$sample": {"size": 1}}]))
        return random_record


def fill():
    filler = MongoFiller()
    filler.create_places(5)
    filler.create_users(10)


def multiprocessing_fill(process_number):
    workers = []
    for i in range(process_number):
        workers.append(multiprocessing.Process(target=fill))

    for worker in workers:
        worker.start()

    for worker in workers:
        worker.join()


if __name__ == "__main__":
    fill()

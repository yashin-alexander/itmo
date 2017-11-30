import simpy

env = simpy.Environment()

# scenario variants
MONSTERS_NUMBER =   ((720, 360, 180, 90), (720, 360, 180, 90), (720, 360, 180, 90), (720, 360, 180, 90))
TIMEZONES_NUMBER =  ((8, ),               (10, ),              (8, ),               (8, )              )
VISITING_HOURS =    ((8, ),               (10, ),              (8, ),               (3, )              )
TIMID_CHILDREN =    ((98.92, ),           (98.92, ),           (97, ),              (98.92, )          )

TIMEZONES_POPULATION = {
        'CentralAmerica7': 61209735,
        'CentralAmerica8': 7659561,
        'CentralAmerica9': 25198677,
        'Alaska10': 186197,
        'Alaska11': 14142,
        'Kamchatka12': 2356,
        'NewZeland11': 2241,
        'Australia10': 7988302,
        'Japan9': 47664541,
        'China8': 351527847  # fix the populations pls
        }


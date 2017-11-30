# scenario variants
MONSTERS_NUMBER =   ((720, 360, 180, 90), (720, 360, 180, 90), (720, 360, 180, 90), (720, 360, 180, 90))
TIMEZONES_NUMBER =  ((8, ),               (10, ),              (8, ),               (8, )              )
VISITING_HOURS =    ((8, ),               (10, ),              (8, ),               (3, )              )
TIMID_CHILDREN =    ((98.92, ),           (98.92, ),           (97, ),              (98.92, )          )

TIMEZONES_POPULATION = {
    -11: 55593,
    -10: 1713232,
    -9: 741174,  # and -9:30
    -8: 61526901,
    -7: 32872730,
    -6: 240604308,
    -5: 301108339,
    -4: 99579743,  # and -3:30
    -3: 241618458,
    -2: 2784,
    -1: 773395,
    0: 252535680,
    +1: 779333045,
    +2: 445080472,
    +3: 585168798,
    +4: 152492005,  # and +3:30 and +4:30
    +5: 1601497717,  # and +5:30
    +6: 293582086,  # and +6:30 and +5:45
    +7: 398538888,
    +8: 1578332933,  # and +08:45
    +9: 213545890,  # and +8:30 and +9:30
    +10: 31400559,
    +11: 1919388,  # and +10:30
    +12: 6157999  # and +12:45
    }

HOUR = 3600
DURATION_OF_FRIGHT = (10, 18)


if __name__ == "__main__":
    print(sum(TIMEZONES_POPULATION.values()))


anylogic_buffer = 49500

timezones = {'CentralAmerica7': 61209735,
'CentralAmerica8': 7659561,
'CentralAmerica9': 25198677,
'Alaska10': 186197,
'Alaska11': 14142,
'Kamchatka12': 2356,
'NewZeland11': 2241,
'Australia10': 7988302,
'Japan9' : 47664541,
'China8' : 351527847
}
updated_timezones = {}

for key in timezones.keys():
    element = timezones.get(key)/10000
    print(key, '=', element)


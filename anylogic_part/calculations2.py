anylogic_buffer = 49500

timezones = {'China8' : 40152,
'Bangladesh7': 5297,
'India' : 3000
#33411
}
updated_timezones = {}

for key in timezones.keys():
    element = timezones.get(key)
    print(key, '=', element)


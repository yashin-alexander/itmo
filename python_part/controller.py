import runner
import table
from constants import TIMEZONES_POPULATION, TIMEZONES_NUMBER

out = table.Table()
# out.open_table(("helle", "wedwe", "edwed",))


TIMEZONES_POPULATION.update((x, int(round((y*25.44/100000)+5, 0))) for x, y in TIMEZONES_POPULATION.items())

print(TIMEZONES_POPULATION)

print("\n-----------------------------\n")
worker = runner.Runner()
# queue_length, proc_doors, monsters_stuck, productivity = worker.execute(0, 0)
worker.execute(0, 0)
worker = runner.Runner()
worker.execute(0, 1)
worker = runner.Runner()
worker.execute(0, 2)
worker = runner.Runner()
worker.execute(0, 3)

print("\n-----------------------------\n")
worker = runner.Runner()
worker.execute(1, 0)
worker = runner.Runner()
worker.execute(1, 1)
worker = runner.Runner()
worker.execute(1, 2)
worker = runner.Runner()
worker.execute(1, 3)

print("\n-----------------------------\n")
worker = runner.Runner()
worker.execute(2, 0)
worker = runner.Runner()
worker.execute(2, 1)
worker = runner.Runner()
worker.execute(2, 2)
worker = runner.Runner()
worker.execute(2, 3)

print("\n-----------------------------\n")
worker = runner.Runner()
worker.execute(3, 0)
worker = runner.Runner()
worker.execute(3, 1)
worker = runner.Runner()
worker.execute(3, 2)
worker = runner.Runner()
worker.execute(3, 3)

setup:
	python3 -m pip install numpy
	python3 -m pip install simpy
	@echo "Up to date!"
run:
	python3 modeling/queuing_systems_modeling.py
clean:
	rm -rf modeling/__pycach*

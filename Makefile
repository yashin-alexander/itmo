setup:
	python3 -m pip install numpy
	python3 -m pip install simpy
	python3 -m pip install stats
	@echo "Up to date!"
run:
	python3 modeling/controller.py
clean:
	rm -rf modeling/__pycach*

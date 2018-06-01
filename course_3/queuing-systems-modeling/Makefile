setup:
	python3 -m pip install numpy
	python3 -m pip install simpy
	python3 -m pip install stats
	@echo "Up to date!"
run_exp_exp_exp:
	python3 modeling/controller.py --mod1 exp --mod2 exp --mod3 exp
run_exp_det_uni:
	python3 modeling/controller.py --mod1 exp --mod2 det --mod3 uni
run_exp_erl_uni:
	python3 modeling/controller.py --mod1 exp --mod2 erl --mod3 uni
clean:
	rm -rf modeling/__pycach*

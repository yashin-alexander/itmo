import simpy

F = len("Канукова")
I = len("Софья")
K = (2 + (I % 7))
MB = F
LAMBDA = (K*0.9/F)
Q = round(F/(F+I), 3)
E2 = 3 + (F % 5)
E3 = 9 - E2

TRANSACTIONS_NUMBER = 100000

env = simpy.Environment()
system_1 = simpy.Resource(env, capacity=K)
system_2 = simpy.Resource(env, capacity=1)
system_3 = simpy.Resource(env, capacity=1)

system_2_enter_time = 0
system_3_enter_time = 0

queue_1_waiting_time = []
queue_2_waiting_time = []
queue_3_waiting_time = []

queue_1_length = []
queue_2_length = []
queue_3_length = []

lost_2 = 0
lost_3 = 0

system_1_service_duration = []
system_2_service_duration = []
system_3_service_duration = []

global_time = []

system_1_transactions_intervals = []
system_2_transactions_intervals = []
system_3_transactions_intervals = []


def print_values():
    print("F = ", F)
    print("I = ", I)
    print("K = ", K)
    print("M[b] = ", MB)
    print("lambda = ", LAMBDA)
    print("Q = ", Q)
    print("E1 = inf")
    print("E2 = ", E2)
    print("E3 = ", E3)


if __name__ == "__main__":
    print_values()

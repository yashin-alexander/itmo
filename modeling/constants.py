import simpy


F = len("Яшин")
I = len("Александр")
K = 2 + (F % 7)
# K = 1
MB = F
LAMBDA = (K*0.9/F)
Q = round(F/(F+I), 3)
E2 = 3 + (F % 5)
E3 = 9 - E2

TRANSACTIONS_NUMBER = 10

env = simpy.Environment()
system_1 = simpy.Resource(env, capacity=K)
system_2 = simpy.Resource(env, capacity=1)
system_3 = simpy.Resource(env, capacity=1)


queue_1_len = 0
queue_2_len = 0
queue_3_len = 0


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

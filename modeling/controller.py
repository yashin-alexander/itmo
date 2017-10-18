import queuing_systems_modeling

first_test_result = queuing_systems_modeling.calculate()
second_test_result = queuing_systems_modeling.calculate()

print(first_test_result, "\n")
print(second_test_result, "\n")

result = []
for i in range(len(first_test_result)):
    result.append((first_test_result[i] + second_test_result[i]) / 2)

print(result)

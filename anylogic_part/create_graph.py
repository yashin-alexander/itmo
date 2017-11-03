import matplotlib.pyplot as plt
import os
import sys

plt.xlabel('try number')
plt.ylabel('queue size')
plt.plot([1,2,3,4], [12.042, 31.56, 83.28, 1379],  color='green')
plt.axis([1, 4, 0, 1500])

plt.legend()
path = "./screens/"
if not os.path.exists(path):
    os.mkdir(path)
plt.savefig('{}{}.png'.format(path, "queue_size_1"))
plt.close()


plt.xlabel('try number')
plt.ylabel('broken monsters %')
plt.plot([1,2,3,4], [110/7.20, 113/3.60, 113/1.80, 90/0.90], color='blue')
plt.axis([1, 4, 0, 100])

plt.legend()
path = "./screens/"
if not os.path.exists(path):
    os.mkdir(path)
plt.savefig('{}{}.png'.format(path, "broken_monsters_1"))
plt.close()


plt.xlabel('try number')
plt.ylabel('roductivity')
plt.plot([1,2,3,4], [1, 1, 1, 8325/10832],  color='red')
plt.axis([1, 4, 0, 1.1])

plt.legend()
path = "./screens/"
if not os.path.exists(path):
        os.mkdir(path)
plt.savefig('{}{}.png'.format(path, "productivity_1"))
plt.close()

##

plt.xlabel('try number')
plt.ylabel('queue size')
plt.plot([1,2,3,4], [474, 2210, 2200, 2145],  color='green')
plt.axis([1, 4, 0, 2300])

plt.legend()
path = "./screens/"
if not os.path.exists(path):
    os.mkdir(path)
plt.savefig('{}{}.png'.format(path, "queue_size_2"))
plt.close()


plt.xlabel('try number')
plt.ylabel('broken monsters %')
plt.plot([1,2,3,4], [187/7.20, 356/3.60, 180/1.80, 90/0.90], color='blue')
plt.axis([1, 4, 0, 100])

plt.legend()
path = "./screens/"
if not os.path.exists(path):
    os.mkdir(path)
plt.savefig('{}{}.png'.format(path, "broken_monsters_2"))
plt.close()


plt.xlabel('try number')
plt.ylabel('roductivity')
plt.plot([1,2,3,4], [1, 29995/48300, 15367/48300, 8324/48300],  color='red')
plt.axis([1, 4, 0, 1.1])

plt.legend()
path = "./screens/"
if not os.path.exists(path):
        os.mkdir(path)
plt.savefig('{}{}.png'.format(path, "productivity_2"))
plt.close()

##

plt.xlabel('try number')
plt.ylabel('queue size')
plt.plot([1,2,3,4], [12, 40, 2918, 6122],  color='green')
plt.axis([1, 4, 0, 7000])

plt.legend()
path = "./screens/"
if not os.path.exists(path):
    os.mkdir(path)
plt.savefig('{}{}.png'.format(path, "queue_size_3"))
plt.close()


plt.xlabel('try number')
plt.ylabel('broken monsters %')
plt.plot([1,2,3,4], [308/7.20, 315/3.60, 180/1.80, 90/0.90], color='blue')
plt.axis([1, 4, 0, 100])

plt.legend()
path = "./screens/"
if not os.path.exists(path):
    os.mkdir(path)
plt.savefig('{}{}.png'.format(path, "broken_monsters_3"))
plt.close()


plt.xlabel('try number')
plt.ylabel('roductivity')
plt.plot([1,2,3,4], [1, 1, 6134/10630, 3042/48300],  color='red')
plt.axis([1, 4, 0, 1.1])

plt.legend()
path = "./screens/"
if not os.path.exists(path):
        os.mkdir(path)
plt.savefig('{}{}.png'.format(path, "productivity_3"))
plt.close()


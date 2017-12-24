# Часть 1

## Описание процесса:

В Корпорации монстров монстры пугают детей. Они делают это каждый день с 9:00 до 18:00, за время работы монстров на Земле проходит 9 часов. Монстры начинают работать, когда наступает ночь в Центральной Америке(-6 UTC) и заканчивают, когда наступает ночь в Австралии(10 UTC).  

- Монстры пугают детей каждый день с 9:00 до 18:00.
- Монстры начинают пугать в определенном часовом поясе, и заканчивают в другом поясе со смещением в 9 часов, пугая при этом в каждом поясе по часу.

#### Сведения об обслуживании

Для того, чтобы напугать ребенка, монстр должен зайти в дверь, где тот спит. Если ребенок коснется монстра, монстр не сможет пугать детей до конца рабочего дня. Дети бывают пугливые, пугливые дети точно не будут трогать ребенка. Двери от дестких комнат появляются перед монстром по следующим правилам:

- К монстру должна приехать дверь, после чего он заходит в нее.
- Монстр проведет за дверью некоторое количество времени, зависящее от пугливости ребенка (пугливый ребенок испугается быстрее).
- Монстр выходит из двери, и после некоторого ожидания к нему подвозят следующую дверь (дверь подъезжает за константное время).
- В случае, если монстр находится за дверью на момент конца рабочего дня, он должен напугать ребенка, и только потом выйти.
- Двери поступают в корпорацию, пока есть неиспуганные дети в часовом поясе.
- Смелый ребенок коснется монстра. (существует вероятность появления смелого ребенка).
- Пугливый ребенок никогда не коснется монстра.

Монстры пугают детей для того, чтобы наполнять капсулы с детскими криками, каждый монстр имеет счетчик накопленных детских криков.
Чтобы монстр работал, ему нужен напарник. Их труд оплаччивает корпорация.

#### Данные для моделирования:

- Часы работы: 				9 часов
- Часовые пояса, где пугают детей: 	Центральная Америка---Австралия
- Населенность часового пояса: 		реальные данные[1]
- Процент детей среди всех людей:	реальные данные, 25.44%[2]
- Процент пугливых детей:		реальные данные, 3%[3]
- Длительность пугания:			14 секунд[4]
- Количество монстров:			720 монстров[5]
- Процент смелых детей:			0.0108%[6]
- Частота поступления дверей:    7 секунд[7]


#### Критерий эффективности:

- Количество накопленных детских криков / (зарплата монстра * 2)

#### Сценарии работы системы

 1) Стандартный, описан выше.
 2) С изменением часовых поясов. Текущий выбор (США-Восточная Европа) включит в себя большое количество заявок, система может быть не самой эффективной. Если выбирать другие пояса (Австралия-Восточная Европа), система может быть эффективней. 
 3) С изменением времени работы. Т.к. время работы возрастет, количество монстров, которых потрогали дети, вырастет, что негативно скажется на средней эффективности работы. Т.к. монстры будут простаивать зря.
 4) С увеличением процента смелых детей.


#### Источники

- [1] - https://www.sporcle.com/games/segacs/time-zones-in-order-of-population , check info/real_info/time-zones to get it.
- [2] - https://www.indexmundi.com/world/age_structure.html
- [3] - Abdnormal child psychology, second edition. Wadsworth 2003. page 417
- [4] - (10+18)/2 = 14    0:14:08 (10 с.), 0:14:10 (18 с.) Мультфильм.
- [5] - 12*60=720         12 монстров в ангаре (monsters_number). Всего 60 ангаров (inc). Мультфильм. 
  ![alt tag](https://raw.githubusercontent.com/yashin-alexander/Monsters-Inc-modeling/master/info/screens/monsters_number.png)
  ![alt tag](https://raw.githubusercontent.com/yashin-alexander/Monsters-Inc-modeling/master/info/screens/inc.png)

- [6] - 1/92 = 0.0108     За один рабочий день один монстр испугался (scared_monster). К тому времени было напугано уже 92 ребенка (children_scared). Мультфильм.
  ![alt tag](https://raw.githubusercontent.com/yashin-alexander/Monsters-Inc-modeling/master/info/screens/children_scared.png)
- [7] Двери приходят в очередь каждые 7 секунд. Мультфильм. 

## Часть 2

# Сценарий №1

#### 1 опыт
720 монстров, 8 часовых поясов, 8 часов работы, 98.92% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/1info.PNG?raw=true)

#### 2 опыт 
360 монстров, 8 часовых поясов, 8 часов работы, 98.92% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/2info.PNG?raw=true)

#### 3 опыт
180 монстров, 8 часовых поясов, 8 часов работы, 98.92% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/3info.PNG?raw=true)

#### 4 опыт
90 монстров, 8 часовых поясов, 8 часов работы, 98.92% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/4info.PNG?raw=true)

для сценария 1 характерны следующие особенности:
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/queue_size_1.png?raw=true)
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/broken_monsters_1.png?raw=true)
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/productivity_1.png?raw=true)

# Сценарий №2
#### 5 опыт
720 монстров, 10 часовых поясов, 10 часов работы, 98.92% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/5info.PNG?raw=true)

#### 6 опыт
360 монстров, 10 часовых поясов, 10 часов работы, 98.92% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/6info.PNG?raw=true)

#### 7 опыт
180 монстров, 10 часовых поясов, 10 часов работы, 98.92% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/7info.PNG?raw=true)

#### 8 опыт
90 монстров, 10 часовых поясов, 10 часов работы, 98.92% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/8info.PNG?raw=true)

для сценария 2 характерны следующие особенности:
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/queue_size_2.png?raw=true)
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/broken_monsters_2.png?raw=true)
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/productivity_2.png?raw=true)


# Сценарий №3
#### 9 опыт
720 монстров, 8 часовых поясов, 8 часов работы, 97% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/9.PNG?raw=true)

#### 10 опыт 
360 монстров, 8 часовых поясов, 8 часов работы, 97% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/10.PNG?raw=true)

#### 11 опыт
180 монстров, 8 часовых поясов, 8 часов работы, 97% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/11.PNG?raw=true)

#### 12 опыт
90 монстров, 8 часовых поясов, 8 часов работы, 97% несмелых детей 
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/12.PNG?raw=true)

для сценария 3 характерны следующие особенности:
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/queue_size_3.png?raw=true)
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/broken_monsters_3.png?raw=true)
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/productivity_3.png?raw=true)



# Сценарий №4

#### 13 опыт
720 монстров, 8 часовых поясов(забитие очереди происходит в первых трех), 3 часа работы, 98.92% очень смелых детей. Монстры не пугаются.
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/13.PNG?raw=true)

#### 14 опыт
360 монстров, 8 часовых поясов(забитие очереди происходит в первых трех), 8 часов работы, 98.92% очень смелых детей.
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/14.PNG?raw=true)

#### 15 опыт
180 монстров, 8 часовых поясов(забитие очереди происходит в первых трех), 8 часов работы, 98.92% очень смелых детей.
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/15.PNG?raw=true)

#### 16 опыт
90 монстров, 8 часовых поясов(забитие очереди происходит в первых трех), 8 часов работы, 98.92% очень смелых детей.
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/16.PNG?raw=true)
 
для сценария 4 характерны следующие особенности:
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/queue_size_4.png?raw=true)
![alt tag](https://github.com/yashin-alexander/Monsters-Inc-modeling/blob/master/anylogic_part/screens/productivity_4.png?raw=true)


В связи с тем, что свободно распространяемая версия anylogic позволяет генерировать только 50000 заявок, реальное количество детей было уменьшено ~ в 1000 раз. 

## Часть 3

# Реализация на Python

- моделирование проведено средствами simpy
- реализован подсчит доверительного итервала для нескольких запусков


Пример вывода программы:
(90 монстров, 8 часовых поясов, 8 часов работы, 98.92% несмелых детей)
```
Not processed doors 14081.375+-261.10631464381515. 
Monsters stuck 90.0+-0.0. 
Processed doors 8529.625+-261.10631464381515 
Monster productivity 0.41914825134275846+-0.01283084018318592
```

(720 монстров, 8 часовых поясов, 10 часов работы, 98.92% несмелых детей)
```
Not processed doors 223.375+-10.017699180700127. 
Monsters stuck 223.375+-10.017699180700127. 
Processed doors 22387.625+-10.017699180700127 
Monster productivity 0.13751679983685422+-6.153408113000524e-05
```

(180 монстров, 8 часовых поясов, 8 часов работы, 97% несмелых детей)
```
Not processed doors 81.25+-3.8073662156404127. 
Monsters stuck 81.25+-3.8073662156404127. 
Processed doors 8340.75+-3.8073662156404127 
Monster productivity 0.4098668789527221+-0.00018709508231688936
```

Одинаковые доверительные интервалы для характеристик дверей обусловлены тем, что количетсво обработанных и необработанных
дверей - обратные величины.

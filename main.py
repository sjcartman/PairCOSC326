import numpy as np
import random
column_name = [('arr_floor', int), ('dest_floor', int), ('time', int), ('on_elv', int)]
door_time = 10
floor_time = 5
people = np.array([])
elv_floor = 0
times = 100
chance = 0.1
dest = 0
buttons_pressed = np.zeros(shape=(10))
going_up = True


def gen_people():
    global people, chance, buttons_pressed
    for i in range(0, 10):
        r = random.random()
        if r < chance:
            x = random.randint(0, 9)
            if x != i:
                people = people.tolist()
                buttons_pressed[i] = 1
                people.append((i, x, 0, 0))
                people = np.array(people, dtype=column_name)




def let_people_on():
    global people, buttons_pressed, elv_floor
    # check if there are new passenegers the want to go on and have them press buttons
    for j in people:
        if j["arr_floor"] == elv_floor:
            j['on_elv'] = 1
            buttons_pressed[j['dest_floor']] = 1


def let_people_off():
    global people, elv_floor
    if len(people) > 0 :
        a = people["dest_floor"] == elv_floor
        b = people["on_elv"] == 1
        c = np.logical_and(a, b)
        x = people[c]
        f = open("times.txt", "a")
        for i in x['time']:
            f.write(str(i) + "\n")
        f.close()
        a = people["dest_floor"] != elv_floor
        b = people["on_elv"] != 1
        c = np.logical_or(a, b)
        people = people[c]


def open_door():
    global people, column_name, door_time
    if len(people) > 0 :
        people['time'] = people['time'] + door_time


def move_elevator():
    global people, column_name, floor_time
    if len(people) > 0:
        people['time'] = people['time'] + floor_time


def up():
    global elv_floor
    elv_floor += 1
    move_elevator()


def down():
    global elv_floor
    elv_floor -= 1
    move_elevator()


def get_dest():
    up_then_down()

def up_then_down():
    global elv_floor, dest, going_up, buttons_pressed
    if elv_floor == 9:
        going_up = False
    elif elv_floor == 0:
        going_up = True
    if going_up:
        for i in range(elv_floor+1, 10):
            if buttons_pressed[i] == 1:
                dest = i
                return
    else:
        for i in range(0, elv_floor):
            if buttons_pressed[i] == 1:
                dest = i
                return

    if not going_up:
        for i in range(elv_floor+1, 10):
            if buttons_pressed[i] == 1:
                dest = i
                return
    else:
        for i in range(0, elv_floor):
            if buttons_pressed[i] == 1:
                dest = i
                return


def sim():
    global people, times, chance, elv_floor, dest, buttons_pressed
    elv_floor = random.randint(0, 10)
    print()
    i = 0
    get_dest()
    while len(people) > 0 or i <= 100:
        # generate more people
        if i < 100:
            gen_people()

        if elv_floor == dest :
            get_dest()
            buttons_pressed[elv_floor] = 0
            open_door()
            let_people_off()
            let_people_on()

        elif dest > elv_floor:
            up()

        elif dest < elv_floor:
            down()

        i += 1
        print(str(i) + " : "+ str(len(people)) +" , "+ str(dest)+" , "+ str(elv_floor))
sim()







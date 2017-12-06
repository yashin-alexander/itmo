import cx_Oracle
import sys
from collections import namedtuple
from recordtype import recordtype

table = namedtuple('Name', 'name id_field fields')

Service     = table("service",      "serviceId",    ("serviceName",))
Post        = table("post",         "postId",       ("postName", "salary", "serviceId"))
Staff       = table("staff",        "StaffId",      ("name", "postId"))
Staging     = table("staging",      "StagingId",    ("stagingName", "stagingPrice", "description", "durationTime"))
Role        = table("role",         "RoleId",       ("roleName", "stagingId"))
Cast        = table("cast",         "CastId",       ("eventId", "personId"))
Requisite   = table("requisite",    "RequisiteId",  ("requisiteName",))
Decoration  = table("decoration",   "DecorationId", ("stagingId", "requisiteId"))
Place       = table("place",        "PlaceId",      ("placeType", "placePrice"))
Timetable   = table("timetable",    "TimetableId",  ("eventType", "eventDate", "stagingId"))
Booking     = table("booking",      "BookingId",    ("eventId", "placeId", "placeNo"))

staging = recordtype('Name', 'name price poster_text poster_pic presale duration event_date person1 person2 person3')

tables = (Service,
          Post,
          Staff,
          Staging,
          Role,
          Cast,
          Requisite,
          Decoration,
          Place,
          Timetable,
          Booking)

operations = ("create",
              "read",
              "update",
              "delete")

package_functions = ("delete",
                     "priceup",
                     "expen",
                     "pricedown",
                     "addstaging")

connection = cx_Oracle.connect('sonya/xkl088@localhost:1521')
cursor = connection.cursor()


def fixup_fields(fields):
    if len(fields) == 1:
        fields = '({})'.format(fields[0])
    else:
        fields = str(fields).replace('\'', '')
    return fields


def fixup_values(values):
    values = str(values).replace("[", "(")
    values = values.replace("]", ")")
    if len(values) == 1:
        values = '(\'{}\')'.format(values[0])
    return values


def create(table, values):
    fields = fixup_fields(table.fields)
    values = fixup_values(values)
    try:
        query = "INSERT INTO {} {} VALUES {}".format(table.name, fields, values)
        cursor.execute(query)
        connection.commit()
    except cx_Oracle.DatabaseError:
        info_string = "Specify '{}' correctly to commit create operation for '{}' table".format(fields, table.name)
        print(info_string)


def read(table):
    query = 'SELECT * FROM {}'.format(table.name)
    cursor.execute(query)
    for result in cursor:
        print(result)


def update(table, values):
    query = "UPDATE {} SET {}={} WHERE {}={}".format(table.name, values[1], values[2], table.id_field ,values[0])
    print(query)
    try:
        cursor.execute(query)
        connection.commit()
    except cx_Oracle.DatabaseError:
        info_string = "Specify '{}', field to update: {}, value correctly to commit update operation for '{}' table".format(table.id_field, table.fields, table.name)
        print(info_string)


def delete(table, value):
    value = value[0]
    query = 'DELETE FROM {} WHERE {}={}'.format(table.name, table.id_field, value)
    print(query)
    try:
        cursor.execute(query)
        connection.commit()
    except cx_Oracle.DatabaseError:
        info_string = "Specify '{}' correctly to commit delete operation for '{}' table".format(table.id_field, table.name)
        print(info_string)


def process_operation(table, operation, fields_info):
    if operation == "create":
        create(table, fields_info)
    elif operation == "read":
        read(table)
    elif operation == "update":
        update(table, fields_info)
    elif operation == "delete":
        delete(table, fields_info)


def process_function(func):
    if func == "delete":
        delete_all()
    elif func == "priceup":
        prices_up()
    elif func == "expen":
        print_theater_expenses()
    elif func == "pricedown":
        prices_down()
    elif func == "addstaging":
        add_staging()


def prices_up():
    cursor.execute('BEGIN UTIL.UPD_PRICES_FOR_5_PERCENT; END;')
    connection.commit()


def prices_down():
    cursor.execute('BEGIN UTIL.UPD_PRICES_FOR_5_PERCENT_DOWN; END;')
    connection.commit()


def delete_all():
    cursor.execute('BEGIN UTIL.delete_all_the_theater; END;')
    connection.commit()


def print_theater_expenses():
    myvar = cursor.callfunc('UTIL.read_theater_expenses', cx_Oracle.NUMBER)
    print(myvar)


def parse_staging_parameters(params):           # method to parse parameters for staging
    staging_list = staging('staging_list')      # не уверен что это будет работать так, если не скмпилится - эту строку поменять
                                                # staging_list создан ссверху

    with open(params[3], "r") as picture:
        picture_as_string = picture.readlines()

    staging_list.name           = params[0]
    staging_list.price          = params[1]
    staging_list.poster_text    = params[2]
    staging_list.poster_pic     = picture_as_string
    staging_list.presale        = params[4]
    staging_list.duration       = params[5]
    staging_list.event_date     = params[6]
    staging_list.person1        = params[7]
    staging_list.person2        = params[8]
    staging_list.person3        = params[9]


def add_staging(parameters):  # сюда надо будет передать нормальные параметры. ух надо будет как то распарсить до этого
    parse_staging_parameters(parameters)
    description = cursor.callfunc('UTIL.create_staging', cx_Oracle.OBJECT, parameters=parameters)


if __name__ == '__main__':
    if sys.argv[1] in package_functions:
        process_function(sys.argv[1])
        exit(0)

    for table in tables:
        if sys.argv[1] == table.name and sys.argv[2] in operations:
            process_operation(table, sys.argv[2], sys.argv[3:])
            exit(0)
    print("Specify table & operation correctly")

    cursor.close()
    connection.close()

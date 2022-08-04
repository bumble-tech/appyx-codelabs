#!/usr/bin/env python3
import sys
import os
import re

CODELAB_NAME_PATTERN = "([0-9]{3}).md"
MD_PATH = "../markdown/"
DOC_PATH = "../html"

def parseCodelabId(lastId):
    return str(lastId).zfill(3)

def generateHTML(id):
    try:
        filePath = MD_PATH+str(id)+".md"
        os.path.exists(filePath)
        print("HTML will be generated for file ", filePath)
        command = "claat export -o {} {}".format(DOC_PATH,filePath)
        os.popen(command)
    except Exception as e:
        print("Couldn't generate files for ", filePath)
        print(str(e) + "\n\n")
        print("Did you provide the correct id?")
        print("Use \"new\" to create a new codelab. Example: ./codelab.py new")

    return

def getCodelabId(parameters):
    if len(sys.argv) == 3:
        return str(parameters[2])
    else:
        return parseCodelabId(getLastCodelabId())

def getLastCodelabId():
    pattern = re.compile(CODELAB_NAME_PATTERN)
    ids = map(lambda x: x[0:3], [k for k in os.listdir(MD_PATH) if pattern.match(k)])
    lastId = max(ids)
    return int(lastId)

def createNewCodelab():
    newId = getLastCodelabId()+1
    codeLabId = parseCodelabId(newId)
    fileName = MD_PATH+codeLabId+".md"
    template = open('template.md', 'r')
    newCodelab = open(fileName, 'w')
    newCodelab.write("id: {} \n".format(codeLabId))

    author = os.popen('git config user.name').read()
    newCodelab.write("author: {}".format(author))
    #todo author
    newCodelab.write(template.read())
    print("Codelab file succesfully created as ", str(fileName))
    return

def printMan():
    print(" Use \"new\" to create a new codelab. Example: ./codelab.py new")
    print(" Use \"gen\" to generate the html for the latest codelab markdown. Example: ./codelab.py gen")
    print(" Use \"gen\" \{codelabId\} to generate the html for the latest codelab markdownExample: ./codelab.py gen 001")

def generatCodelab(parameters):
    pattern = re.compile(CODELAB_NAME_PATTERN)
    codeLabId = getCodelabId(parameters)
    codeLabFile = codeLabId+".md"
    if pattern.match(codeLabFile):
        generateHTML(codeLabId)
    else:
        print("Couldn't find codelab file {}".format(codeLabFile))
        printMan()


def checkArg(parameters):
    argument = ""
    try:
        argument = str(parameters[1])
    except:
        print("Missing arguments.")
        printMan()
        return

    if str(parameters[1]) == "new":
        createNewCodelab()
    elif str(parameters[1]) == "gen":
        generatCodelab(parameters)
    else:
        print("Wrong arguments.")
        printMan()

checkArg(sys.argv)


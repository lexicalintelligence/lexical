# Copyright 2015 Lexical Intelligence, LLC.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

"""
A Lexical client for Python 3
"""

__version__ = "0.0.1"

import urllib.request
import urllib.parse
import json

class LexicalQuery:
    def __init__(self):
        self.fields = {}
        self.fields['expandAbbreviations'] = True
        self.fields['expandCoordinations'] = True        
        self.fields['detectNegation'] = True
        self.fields['checkSpelling'] = True
        self.fields['showEntities'] = True
        self.fields['showTokens'] = True
        self.fields['showNounPhrases'] = False

    def id(self, id):
        self.fields['id'] = id
        return self

    def addText(self, field, text):
        if field not in self.fields:
            self.fields[field] = [text]
        else:
            self.fields[field].append(text)
        return self

    def expandAbbreviations(self, expandAbbreviations=True):
        self.fields['expandAbbreviations'] = expandAbbreviations
        return self
  
    def expandCoordinations(self, expandCoordinations=True):
        self.fields['expandCoordinations'] = expandCoordinations
        return self
  
    def detectNegation(self, detectNegation=True):
        self.fields['detectNegation'] = detectNegation
        return self
  
    def checkSpelling(self, checkSpelling=True):
        self.fields['checkSpelling'] = checkSpelling
        return self
  
    def showEntities(self, showEntities=True):
        self.fields['showEntities'] = showEntities
        return self
  
    def showTokens(self, showTokens=True):
        self.fields['showTokens'] = showTokens
        return self

    def showNounPhrases(self, showNounPhrases=True):
        self.fields['showNounPhrases'] = showNounPhrases
        return self

class LexicalClient:
    def __init__(self, url):
        self.url = url

    def process(self, query):
        data = urllib.parse.urlencode(query.fields, True)
        req = urllib.request.Request(self.url + "/extract", data.encode('utf-8'))
        response = urllib.request.urlopen(req)
        return json.loads(response.read().decode('utf-8'))

    def process_batch(self, queries):    
        data = json.dumps([q.fields for q in queries])
        req = urllib.request.Request(self.url + "/extract/batch", data.encode('utf-8'), {'content-type': 'application/json'})
        response = urllib.request.urlopen(req)
        return json.loads(response.read().decode('utf-8'))


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

class LexicalDocument:
    def __init__(self):
        self.data = {}

    def setText(self, field, text):
        if field != None and text != None:
            self.data[field] = [text]
        return self

    def addText(self, field, text):
        if field not in self.data:
            self.setText(field, text)
        else:
            self.data[field].append(text)
        return self

class LexicalQuery:
    def __init__(self):
        self.params = {}
        self.params['expandAbbreviations'] = True
        self.params['expandCoordinations'] = True
        self.params['showTokens'] = True
        self.params['showNounPhrases'] = False
        self.doc = LexicalDocument()

    def expandAbbreviations(self, expandAbbreviations=True):
        self.params['expandAbbreviations'] = expandAbbreviations
        return self
  
    def showTokens(self, showTokens=True):
        self.params['showTokens'] = showTokens
        return self

    def showNounPhrases(self, showNounPhrases=True):
        self.params['showNounPhrases'] = showNounPhrases
        return self

    def setText(self, field, text):
        self.doc.setText(field, text)
        return self

    def addText(self, field, text):
        self.doc.addText(field, text)
        return self

class LexicalClient:
    def __init__(self, url):
        self.url=url
	
    def process(self, query):
        data = {}
        data.update(query.params)
        data.update(query.doc.data)
        data = urllib.parse.urlencode(data, True)
        req = urllib.request.Request(self.url + "/extract", data.encode('utf-8'))
        response = urllib.request.urlopen(req)
        return json.loads(response.read().decode('utf-8'))

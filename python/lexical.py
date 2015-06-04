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

class LexicalClient:
	def __init__(self, url):
		self.url = url + "/extract";
		self.expandAbbreviations = True
		self.expandCoordinations = True
		self.detectNegation = True
		self.checkSpelling = True
	
	def process(self, text):
		data = urllib.parse.urlencode( { 'text': text,
										 'expandAbbreviations': self.expandAbbreviations,
										 'expandCoordinations': self.expandCoordinations,
										 'detectNegation': self.detectNegation,
										 'checkSpelling': self.checkSpelling
  									   } )
		req = urllib.request.Request(self.url, data.encode('utf-8'))
		response = urllib.request.urlopen(req)
		return json.loads(response.read().decode('utf-8'))['entries']
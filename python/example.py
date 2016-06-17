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

from lexical import LexicalClient, LexicalQuery

if __name__ == "__main__":
    lexical = LexicalClient("http://localhost:9898/ner")
    
    query = LexicalQuery()
    query.showTokens(False)
    query.addText('abstract', 'We like health and happiness.').addText('abstract', 'And fruit flies.')

    response = lexical.process(query)['entries']
    
    for field in response:
        entries = response[field]
        for entry in entries:
           print(entry)

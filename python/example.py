from lexical import LexicalClient, LexicalQuery

if __name__ == "__main__":
    lexical = LexicalClient("http://localhost:9898/ner")

    query = LexicalQuery()
    query.showTokens(True)
    query.addText('abstract', 'We like health and happiness.').addText('abstract', 'And fruit flies.')

    response = lexical.process(query)['entries']

    for field in response:
        entries = response[field]
        for entry in entries:
           print(entry)

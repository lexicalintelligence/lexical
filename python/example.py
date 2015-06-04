import lexical as lexicalpy

if __name__ == "__main__":
	lexical = lexicalpy.LexicalClient("https://nlp.lexicalintelligence.com/lexicon/mesh")
	
	entries = lexical.process("Health and happiness")
	for entry in entries:
		print( entry['name'] )
		print( entry['id'] )
		print( entry['type'] )
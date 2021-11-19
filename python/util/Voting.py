# Implementação de uma estrutura de dados de votação
class Voting:
    
    def __init__(self, maximumVotes: int, debug: bool):
        print('Starting voting...')
        self.votes = {}
        self.maximumVotes = maximumVotes
        self.finish = False

    # Inserir um novo voto
    def insertVote(self, to: str):
        return

    # Excluir um voto
    def deleteVote(self, to: str):
        return

    # Retorna o status da votação
    def status(self):
        return

    # Contabilizar quantos votos
    def howManyVotes(self, who: str):
        return

    # Localizar que foi mais votado até o momento
    def whosWinning(self):
        return

    # Localizar que venceu a votação
    def whoWon(self):
        return

    # Finaliza uma votação
    def finish(self):
        return

# Exemplo utilização


print('30 alunos...')
voting2021 = Voting(30)
print('')


voting2021.insertVote('Pedro')
voting2021.insertVote('Maria')
voting2021.insertVote('Joao')
voting2021.insertVote('Joao')
voting2021.insertVote('Pedro')
voting2021.insertVote('Maria')
voting2021.insertVote('Joao')
voting2021.insertVote('Joao')
voting2021.insertVote('Pedro')

voting2021.deleteVote('Pedro')

print('')



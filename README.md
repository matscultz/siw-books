Casi d'uso di SIWBooks
Attori principali
  - Utente occasionale (non registrato)
  - Utente registrato
  - Amministratore

UC01 - Visualizza catalogo libri
Attore principale: Utente occasionale, Utente registrato
Descrizione: L’utente visualizza la lista dei libri presenti nel sistema, con possibilità di navigare pagine.
Precondizione: Il sistema contiene almeno un libro. L’utente accede alla pagina dei libri.
Il sistema mostra l’elenco dei libri con titolo e autori.
L’utente può cliccare su un libro per vedere i dettagli.

UC02 - Visualizza dettagli libro
Attore principale: Utente occasionale, Utente registrato
Descrizione: L’utente visualizza la pagina di dettaglio di un libro, con informazioni estese e recensioni.
Precondizione: Il libro selezionato esiste nel sistema.
Flusso principale: L’utente seleziona un libro dal catalogo. Il sistema mostra titolo, autore/i, anno di rilascio, trama, copertina e recensioni.
L’utente può leggere le recensioni.

UC03 - Scrivere recensione
Attore principale: Utente registrato
Descrizione: L’utente aggiunge una recensione a un libro.
Precondizione: L’utente è autenticato. Il libro esiste.
Flusso principale: L’utente visualizza la pagina del libro.
Clicca su “Aggiungi recensione”. Inserisce titolo della recensione, testo e voto (es. 1-5 stelle).
Il sistema salva la recensione associata all’utente e al libro.

UC04 - Aggiungere libro
Attore principale: Amministratore
Descrizione: L’amministratore aggiunge un nuovo libro al catalogo.
Precondizione: L’amministratore è autenticato.
Flusso principale: L’amministratore accede alla pagina di gestione libri. Clicca su “Aggiungi libro”. Inserisce titolo, autore/i, anno, descrizione, copertina.
Il sistema salva il nuovo libro.

UC05 - Modificare libro
Attore principale: Amministratore
Descrizione: L’amministratore modifica i dati di un libro esistente.
Precondizione: L’amministratore è autenticato. Il libro esiste.
Flusso principale: L’amministratore seleziona un libro. Clicca su “Modifica”. Aggiorna i campi.
Il sistema salva le modifiche.

UC06 - Eliminare libro
Attore principale: Amministratore
Descrizione: L’amministratore elimina un libro dal catalogo.
Precondizione: L’amministratore è autenticato. Il libro esiste.
Flusso principale: L’amministratore seleziona un libro. Clicca su “Elimina”. Il sistema rimuove il libro e tutte le recensioni associate.

UC07 - Gestire autori
Attore principale: Amministratore
Descrizione: L’amministratore può aggiungere, modificare o eliminare autori.
Precondizione: L’amministratore è autenticato.
Flusso principale: L’amministratore accede alla pagina gestione autori. Può aggiungere un nuovo autore, modificarne i dati o eliminarlo.

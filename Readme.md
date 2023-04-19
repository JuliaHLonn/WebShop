Vi har gjort en webbutik för Norra Dalarnas trädgårdstjänst. Här kan man köpa varor i fyra 
kategorier; fröer, blommor, träd och verktyg.

I kodningen har vi tillämpat Spring MVC och delat upp koden i modell, vy och controller. 
Modellen består i två “services”; ShopService, som sköter all logik för inloggning, kundvagn 
och beställning, och EmailSenderService, som sköter sammansättning och skickandet av bekräftelsemail. 
Vyn är de olika HTML-sidorna. De visar bland annat inloggnignssida, butikens huvudsida, produkterna, 
kundvagn och bekräftelsesida. Controllern sköter kopplingen mellan modell och vy. 
Här finns metoder som hämtar användarinput, och skickar dem till modellen. Här finns också metoder som
hämtar data från modellen, och via modellen från databasen, för att visa i vyn. Slutligen pratar modellen 
med databasen, med hjälp av olika Data Access Objects, där den sparar kunder, produkter och beställningar. 
Vyn och controllern kommunicerar aldrig själva med databasen; all data från databasen når endast användaren 
via modellen, som i sin tur hämtar den via DAOs.

När användaren lägger produkter i varukorgen sparas de som “OrderLines”. En orderline består av 
produkt och kvantitet. När en beställning läggs sparas sen varukogen som en beställning, som också är en
lista av orderlines. Det är alltså strikt sett aldrig produkter i sig som direkt sparas i varukorgen och 
beställningar, utan orderlines, eftersom att vi alltid vill ha med kvantiteten av varje vara när vi ser 
beställningens innehåll. När kunden klickar på “köp” så anropas innan en beställning läggs mailservicen 
för att skicka ett bekräftelsemail. Denna hämtar varukorgens innehåll och skickar denna till en 
stringbuilder för att komponera en mail-text som innehåller alla produkter kunden har köpt samt en 
totalsumma. Denna text läggs in i ett mail som skickas till kundens adress. Det är även möjligt att 
logga in som admin, admin kommer till en sida där den kan välja att se ordrar som har skickats, 
se ordrar som inte skickats än och markera dessa som skickade varvid de flyttas till “ordrar som skickats”. 
Admin kan också lägga till nya produkter som ska säljas i webshopen.

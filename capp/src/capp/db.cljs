(ns capp.db
  (:require [clojure.spec.alpha :as s]))

;; spec of app-db
(s/def ::greeting string?)
(s/def ::selected map?)
;(s/def ::selected-list vector?)
(s/def ::app-db
  (s/keys :req-un [::greeting ::login ::selected]))

;; initial state of app-db
(def app-db {:greeting "Hello Clojurescript in Expo!" 
	         :login false
	         :selected {}
	         :selected-list []
	         :master  
						[{:category "BEER" :items [
												{:header true :order "Select BEER" :details ""}	
												{:header true :order "Amstel Super Premium Strong Beer 650ML" :details 	42}	
												{:header false :order "Amstel Super Premium Strong Beer-CAN 500ML" :details 	42}	
												{:header false :order "Budweiser Magnum Strong Beers 650ML" :details 	42}	
												{:header false :order "Budweiser Magnum Strong Beers- 500ML Can" :details 	42}	
												{:header false :order "Budweiser Premium King of Beers 650ML" :details 	42}	
												{:header false :order "Budweiser Premium King of Beers-CANS 500ML" :details 	42}	
												{:header false :order "Carlsberg Elephant Strong Super Premium Beer 650ML" :details 	42}	
												{:header false :order "Carlsberg Elephant Strong Super Premium Beer-Can 500ML" :details 	42}	
												{:header false :order "Heineken Lager Beer 650ML" :details 	42}	
												{:header false :order "Heineken Lager Beer-Cans 500ML" :details 	42}	
												{:header false :order "Hunter Refreshing Strong Premium Beer 650ML" :details 	42}	
												{:header false :order "Kingfisher Premium Lager Beer 650 ML" :details 	42}	
												{:header false :order "Kingfisher Premium Lager Beer Can-500ML" :details 	42}	
												{:header false :order "Kingfisher Premium Lager Beer-CAN 330ML" :details 	42}	
												{:header false :order "Kingfisher Storm Strong Beer 650ML" :details 	42}	
												{:header false :order "Kingfisher Storm Strong Beer-Can 500ML" :details 	42}	
												{:header false :order "Kingfisher Strong Premium  Beer 330ML" :details 	42}	
												{:header false :order "Kingfisher Strong Premium  Beer-CAN 330ML	" :details 	42}
												{:header false :order "Kingfisher Strong Premium  Beer-CAN 500ML	" :details 	42}
												{:header false :order "KingFisher Strong Premium Beer 650 ML" :details 	42}]}
	
						{:category "RUM" :items [

										{:header true :order "Select RUM" :details ""}	
										{:header true :order "Amruts XXX Classic Rum 750ML" :details 	35}	
										{:header false :order "Amruts XXX Classic Rum-(ASPETIC PACK)-180ML" :details 	35}	
										{:header false :order "Amruts XXX Classic Rum-ASPETIC PACK-90ML" :details 	35}	
										{:header false :order "Bacardi Black Rum 180ML" :details 	35}	
										{:header false :order "Bacardi Black Rum 750ML" :details 	35}	
										{:header false :order "Bacardi Carta Blanca Superior White Rum 180ML" :details 	35}	
										{:header false :order "Bacardi Carta Blanca Superior White Rum 750ML" :details 	35}	
										{:header false :order "Bacardi Lemon Original Citrus Rum 750 ML" :details 	35}	
										{:header false :order "Hercules Old Matured Deluxe 3X Rum 375ML" :details 	35}	
										{:header false :order "Hercules Old Matured Deluxe 3X Rum 750ML" :details 	35}	
										{:header false :order "Hercules Old Matured Deluxe 3X Rum 90ML" :details 	35}	
										{:header false :order "Hercules Old Matured Deluxe 3X Rum-Aseptic pack 180ML Aseptic pack" :details 	35}	
										{:header false :order "Khodays XXX Rum 375 ML" :details 	35}	
										{:header false :order "Khodays XXX Rum 750 ML" :details 	35}	
										{:header false :order "Khodays XXX Rum-(ASEPTIC PAK) 180ML	" :details 	35}
										{:header false :order "Khodays XXX Rum-(ASEPTIC PAK) 90ML Aseptic pack" :details 	35}	
										{:header false :order "McDowells No.1 Celebration Deluxe XXX Rum (Tetra pack) 180ML" :details 	35}	
										{:header false :order "McDowells No.1 Celebration Deluxe XXX Rum -Tetra Pack 90ML" :details 	35}	
										{:header false :order "McDowells No.1 Celebration Deluxe XXX Rum 1000ML	" :details 	35}
										{:header false :order "McDowells No.1 Celebration Deluxe XXX Rum 375ML" :details 	35}	
										{:header false :order "McDowells No.1 Celebration Deluxe XXX Rum 750 ML" :details 	35}	
										]}
	
						{:category "WHISKY" :items [

										{:header true :order "Select WHISKY" :details ""}	
										{:header false :order "Chivas Regal Aged 12 years Blended Scotch Whisky" :details 750}	
										{:header false :order "Directors Special Whisky" :details 75}	
										{:header false :order "Directors Special Whisky" :details 20}
										{:header false :order "Directors Special Whisky" :details 30}	
										{:header false :order "Directors Special Whisky-TETRA PACK" :details 300}
										{:header false :order "Directors Special Whisky-Tetrapack" :details 100}
										{:header false :order "DSP Black Deluxe Whisky" :details 23}
										{:header false :order "DSP Black Deluxe Whisky" :details 200}	
										{:header false :order "DSP Black Deluxe Whisky" :details 213}]}
												
											
					    {:category "LAB" :items [
										    {:header true :order "Select LAB" :details ""}	
										    {:header false :order "Bacardi + Cranberry  275ML" :details  	25}	
											{:header false :order "Bacardi Breezer Tropical Cranberry 275ML" :details  	25}	
											{:header false :order "Bacardi Breezer Tropical Orange 275ml (0158)" :details  	25}
											{:header false :order "Bacardi  + Orange Fizz 275ML" :details  	25}]}
												
											
						{:category "VODKA" :items [

										{:header true :order "Select VODKA" :details 	""}	
										{:header false :order "Absolut Vodka 750ML" :details  	25}	
										{:header false :order "Magic Moments PREMIUM Grain Vodka 375ML" :details  	25}	
										{:header false :order "Magic Moments PREMIUM Grain Vodka 750ML" :details  	25}	
										{:header false :order "Muscovy Orange Vodka 180ML" :details  	25}	
										{:header false :order "Muscovy Orange Vodka 750ML" :details  	25}	
										{:header false :order "Platinum Romanov Flavoured Vodka Apple Thrill-180ML" :details  	25}	
										{:header false :order "Platinum Romanov Flavoured Vodka Orange Thrill 180ML" :details  	25}	
										{:header false :order "Platinum Romanov Vodka 180 ML" :details  	25}	
										{:header false :order "Platinum Romanov Vodka 750 ML" :details  	25}]}
												
											
						{:category "BRANDY" :items [

									{:header true :order "Select BRANDY" :details 	""}	
									{:header false :order "Amruts Silver Cup VSOP Brandy-Aseptic Pack 90ML" :details  	25}	
									{:header false :order "Amruts Silver Cup VSOP Brandy-PET 180ML" :details  	25}	
									{:header false :order "Amruts Silver Cup VSOP Brandy-PET 375ML" :details  	25}	
									{:header false :order "Amruts Silver Cup VSOP Brandy-PET 750ML" :details  	25}	
									{:header false :order "Bejois VSOP Brandy-ASPETIC PACK-180ML" :details  	25}	
									{:header false :order "Bejois VSOP Brandy-PET 1000ML" :details  	25}	
									{:header false :order "Chevalier De Paris Special Premium French Brandy 750ML" :details  	25}	
									{:header false :order "Courrier Napoleon Finest French Brandy 180ML" :details  	25}	
									{:header false :order "Courrier Napoleon Finest French Brandy 750ML" :details  	25}	
									{:header false :order "Marnat Napoleon VSOP French Brandy 700ML" :details  	25}]}
												
						
	
						{:category "WINE" :items [
								{:header true :order "Select WINE" :details ""}	
								{:header false :order "Big Banyan Chenin Blanc White Wine 750ML" :details 6}	
								{:header false :order "Big Banyan Langoor Red Wine 750ML" :details 6}	
								{:header false :order "Big Banyan Merlot Red Wine 750ML" :details 6}	
								{:header false :order "Big Banyan Shiraz Red Wine 750ML" :details 6}	
								{:header false :order "Bonini-Cabernet Sauvignon -750ML" :details 6}	
								{:header false :order "Elite Cabernet Sauvignon Red Wine 750ML" :details 6}	
								{:header false :order "Elite Cabernet Shiraz Red Wine 750ML" :details 6}	
								{:header false :order "Elite Red Wine 180ML" :details 6}
								{:header false :order "Elite Red Wine 375ML" :details 6}
								{:header false :order "Elite Red Wine 750ML" :details 6}	
								{:header false :order "Elite Vega Carbonated Red Wine 375ML" :details 6}	
								{:header false :order "Elite White Wine 180ML" :details 6}	
								{:header false :order "Elite White Wine 750ML" :details 6}	
								{:header false :order "Goanas Wine Fortified by Using Neutral Spirit 180ML" :details 6}	
								{:header false :order "Goanas Wine Fortified by Using Neutral Spirit 375ML" :details 6}]}]

	         :items [{:name "Shoes" :price "800"}
	                 {:name "Leg Guards" :price "800"}
	                 {:name "Shirt" :price "300"}
	         		 {:name "Caps" :price "10"}]

	         :kerala [{:category "RUM" :items [["000000","RUM","",""]
	         								   ["13208093X","999 POWER STAR FINE RUM","180"," 140.00"]
											["13258011W","STALLION STRIDE PREMIUM RUM","750"," 	740.00"]
											["13272011W","OLD MAJOR RUM","750"," 	710.00"]
											["13290042X","MURANO RUM","375"," 	460.00"]
											["13291041W","111 OAK WHITE RUM","750"," 	420.00"]
											["13397321X","NO.1 DIPLOMAT CHOICE XXX RUM","750"," 	700.00"] 
											["13407033W","OLD KEMP XXX RUM","180"," 	130.00 "]
											["13074111X","OLD MONK SELECT XXX PREMIUM RUM","750"," 	770.00"] 
											["131007715","HERCULES SPECIAL RESERVE 3'X' RUM","750"," 	680.00 "]
											["13349161X","SPECIAL RESERVE ARISTOCRAT RUM","750"," 	540.00 "]]}

						{:category "VODKA" :items [["000000","VODKA","",""]
												["16001012W","SOUTHERN CHOICE VODKA","375"," 	580.00 "]
												["16013941W","BLUEMAX CRYSTAL VODKA","750","1","200.00 "]
												["160200216","MUSCOVY FINE VODKA","750"," 	520.00"]
												["160641419","ERISTOFF TRIPLE DISTILLED PREMIUM VODKA","750"," 	1120.00"]
												["160701317","BRIHANS DANOVA RUSSIAN VODKA","750"," 	550.00"] 
												["16074011W","NEON GUAVA FLAVOURED VODKA","750"," 	720.00"] 
												["16100022W","SEA ISLAND PREMIUM ORANGE VODKA","375"," 	530.00"] 
												["16157971W","DDL'S FREEZE SPEARMINT VODKA","750"," 	1000.00"] 
												["162017917","PREMIUM POWER PINE APPLE VODKA","750"," 	470.00"]
												["16208021X","OLD HABBIT VODKA","750"," 	810.00"]]}

						{:category "BEER" :items [["000000","BEER","",""]
												["21036054W","ACCORD NO.1 STRONG BEER","650"," 	90.00"] 
												["21036104W","CHENNAI KING NO.1 PREMIUM STRONG BEER","650"," 	120.00"] 
												["21073214W","BIRA 91 BLONDE SUMMER LAGER BEER","650"," 	150.00"] 
												["211230346","CARLSBERG ELEPHANT STRONG SUPER PREMIUM BEER","650"," 	130.00"] 
												["21123184W","TUBORG PREMIUM STRONG BEER","650"," 	100.00"] 
												["21082044W","TAG PREMIUM LAGER BEER","650"," 	120.00"] 
												["213090441","BUDWEISER PREMIUM KING OF BEERS","650"," 	130.00"] 
												["21406054W","STERREN 7 STRONG BEER","650"," 	100.00"] 
												["21412044W","VIIKING GOA STRONG BEER","650"," 	150.00"] 
												["21412104W","KHAJURAHO LAGER BEER","650"," 	130.00"]]} 


						{:category "WINE" :items [["000000","WINE","",""]
												["15209231W","JUBILEE PREMIUM PORT WINE","750"," 	360.00"] 
												["152300310","FRATELLI CHARDONNAY","750"," 	1150.00"] 
												["15230221W","FRATELLI WINES CABERNET SAUVIGNON","750"," 	1050.00"] 
												["152604213","RAYA WHITE WINE","750"," 	370.00"] 
												["152604610","GROVER SELECTE CABERNET SHIRAZ","750"," 	850.00"] 
												["152604814","GROVER ART COLLECTION","750"," 	810.00"] 
												["152605114","VILLA 89 PREMIUM PORT WINE","750"," 	270.00"] 
												["15314122W","BRO CODE ITALIAN SECO","375"," 	180.00"] 
												["15348271X","BIG BANYAN VINEYARDS SHIRAZ RED WINE","750","	1130.00"] 
												["15607181X","SULA VINEYARDS RIESLING","750","	1440.00"]]} 

						{:category "BRANDY" :items [["000000","BRANDY","",""]
												["11001071X","SPI METRO SPECIAL BRANDY","750"," 	410.00"]
												["11001121W","SOUTHERN CHOICE BRANDY","750"," 	1100.00"] 
												["11001131W","JB BRANDY","750"," 	410.00"] 
												["11001141W","GB BRANDY","750"," 	700.00"] 
												["110700618","BRIHAN'S XO PREMIUM BRANDY","750"," 	610.00"] 
												["11070201X","PERUN FRENCH XO BRANDY","750","	1060.00"] 
												["11070211X","FRIENDS V.S.O.P. BRANDY","1000"," 	960.00"] 
												["11070221W","NOVA BRANDY","750"," 	520.00"] 
												["11070231W","BRIMA'S DANNY RESERVE BRANDY","750"," 	910.00"] 
												["11071261W","PRIME MAYER BRANDY","750"," 	610.00"]]}

						{:category "WHISKY" :items [["000000","WHISKY","",""]
												["12001011W","METRO GOLD WHISKY","750"," 	410.00"]
												["12013071W","AMRUT RAJIGALA INDIAN SINGLE MALT WHISKY","750","	5570.00"] 
												["120139612","AMRUT PEATED INDIAN SINGLE MALT WHISKY","750","	5090.00"] 
												["120341318","OFFICER'S CHOICE PRESTIGE WHISKY","750"," 	620.00"] 
												["12081011W","CUTTY SARK BLENDED SCOTCH WHISKY","750","	2850.00"] 
												["122040913","PLANTERS HERITAGE WHISKY","750","1240.00"] 
												["12208041X","BOTTOMS UP WHISKY","750"," 	810.00"] 
												["12290041X","ROCKDOVE PREMIUM WHISKY","750","	1110.00"] 
												["12318011W","ENSO JAPANESE WHISKY","750"," 	6880.00"] 
												["12355031W","PAUL JOHN INDIAN SINGLE MALT WHISKY NIRVANA","750"," 	3090.00"]]}

						{:category "GIN" :items [["000000","GIN","",""]
												["14520011W","NICOL'S SILVER ANCHOR PREMIUM LONDON DRY GIN","750"," 	900.00"]
												["14555011W","BOMBAY BLANCHE CLASSIC ENGLISH DRY GIN","750"," 2610.00 "]
												["14668121W","APPALOOSA GIN","750"," 	650.00"] 
												["14751071W","SAVOY CLUB GIN","750"," 	1210.00"]]}]})




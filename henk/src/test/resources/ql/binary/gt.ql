form taxOfficeExample { 
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
    
  if (hasSoldHouse > hasBoughtHouse) {
  }
}

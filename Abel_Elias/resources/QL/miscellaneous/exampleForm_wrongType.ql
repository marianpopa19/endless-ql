form Box1HouseOwning {
     hasSoldHouse: "Did you sell a house in 2010?" boolean
     hasBoughtHouse: "Did you buy a house in 2010?" boolean
     hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
     if (0.0) {
         sellingPrice: "Price the house was sold for:" money
         privateDebt: "Private debts for the sold house:" money
         valueResidue: "Value residue:" money (sellingPrice - 50.0)
     }
 }
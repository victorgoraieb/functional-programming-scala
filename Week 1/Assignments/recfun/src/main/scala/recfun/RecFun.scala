package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    // Base casis: first and last elements are 1
    if (c==r || c==0) 
      then 1 
    else 
      pascal(c, r-1) + pascal(c-1, r-1)
  }
  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def checkBalance(chars: List[Char], balance: Int): Boolean = {
      //Base case: if string is empty check if final balance is 0
      if chars.isEmpty 
        then balance == 0
      else 
        if chars.head == '(' 
          then checkBalance(chars.tail, balance+1)
        else
          if ((chars.head == ')')) 
            then checkBalance(chars.tail, balance-1) && (balance>0) // Will only proceed if the ) hasn't appear before (
          else 
            checkBalance(chars.tail, balance) //Applies for lists without ( neither )
    }

    checkBalance(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    //Base case: if the money is 0, there's only one combination (no change)
    if money == 0 
      then 1
    else 
      // if there still coins and money remaining, we can branch further and further
      if ((!coins.isEmpty) && (money >0)) 
        then countChange(money - coins.head, coins) + countChange(money, coins.tail)
      // If the list of coins is empty return 0
      else 0
  }
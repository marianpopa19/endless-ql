form Kipling {																			
    
/*
    see: https://www.poetryfoundation.org/poems/46473/if---

        (‘Brother Square-Toes’—Rewards and Fairies)
        
        If you can keep your head when all about you   
            Are losing theirs and blaming it on you,   
        If you can trust yourself when all men doubt you,
            But make allowance for their doubting too;   
        If you can wait and not be tired by waiting,
            Or being lied about, don’t deal in lies,
        Or being hated, don’t give way to hating,
            And yet don’t look too good, nor talk too wise:
        
        If you can dream—and not make dreams your master;   
            If you can think—and not make thoughts your aim;   
        If you can meet with Triumph and Disaster
            And treat those two impostors just the same;   
        If you can bear to hear the truth you’ve spoken
            Twisted by knaves to make a trap for fools,
        Or watch the things you gave your life to, broken,
            And stoop and build ’em up with worn-out tools:
        
        If you can make one heap of all your winnings
            And risk it on one turn of pitch-and-toss,
        And lose, and start again at your beginnings
            And never breathe a word about your loss;
        If you can force your heart and nerve and sinew
            To serve your turn long after they are gone,   
        And so hold on when there is nothing in you
            Except the Will which says to them: ‘Hold on!’
        
        If you can talk with crowds and keep your virtue,   
            Or walk with Kings—nor lose the common touch,
        If neither foes nor loving friends can hurt you,
            If all men count with you, but none too much;
        If you can fill the unforgiving minute
            With sixty seconds’ worth of distance run,   
        Yours is the Earth and everything that’s in it,   
            And—which is more—you’ll be a Man, my son!
        
*/

    q1  :   "Can V.Z. keep his head when all about him\\n Are losing theirs and blaming it on him?" boolean 
   




    if ( q1 ) {

        q2  :   "Can he trust himself when all men doubt him,\\nBut make allowance for their doubting too?" boolean 
        
        if ( q2 ) {
    
            q3  :   "Can he wait and not be tired by waiting?"           boolean 
    
            if ( q3 )  {
            
                positive : "Rudyard Kipling would say: " string( 
                    "<html>                                         "+
                    "   <h2>                                        "+
                    "       <code>                                  "+
                    "           His is the Earth<br/>               "+
                    "           and everything that’s in it,<br/>   "+
                    "           And — which is more —               "+
                    "       </code><br/>                            "+ 
                    "       he’ll be a Man!<br/>                    "+
                    "       <br/>                                   "+
                    "       <i>Rudyard Kipling</i>                  "+
                    "   </h2>                                       "+
                    "</html>"  
                )
            }
        }
    }
}
  
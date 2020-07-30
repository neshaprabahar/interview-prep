# Clarifications for solutions
## NextPrime

logic from https://www.geeksforgeeks.org/program-to-find-the-next-prime-number/

* i will always be odd
* i + 1, i + 3, i + 5 will always be even, since we checked for
     2 we no longer have to check for any other even factor
* As we are incrementing in 6's, i + 4 will always be divisible
     by 3. i + 4 = 9 + k * (6) (0 <= k <= number of iterations)
* The constraint for i is because: if there is a prime factor
     greater than sqrt(n), there has to be one lesser than n^1/2.
     This is because if we had two greater factors, they would
     multiply to be greater than n itself.

package com.cleancoders.primes;

class PrimeGenerator {
    private int[] primes;
    private final int ordMax = 30;
    private int candicate;
    private int primeIndex;
    private boolean possiblyPrime;
    private int ord;
    private int square;
    private int n;
    private final int[] multiples = new int[ordMax + 1];

    public int[] generatePrimes(int numberOfPrimes) {
        primes = new int[numberOfPrimes + 1];
        candicate = 1;
        primeIndex = 1;
        primes[1] = 2;
        ord = 2;
        square = 9;

        while (primeIndex < numberOfPrimes) {
            do {
                candicate = candicate + 2;
                if (candicate == square) {
                    ord = ord + 1;
                    square = primes[ord] * primes[ord];
                    multiples[ord - 1] = candicate;
                }
                n = 2;
                possiblyPrime = true;
                while (n < ord && possiblyPrime) {
                    while (multiples[n] < candicate)
                        multiples[n] = multiples[n] + primes[n] + primes[n];
                    if (multiples[n] == candicate)
                        possiblyPrime = false;
                    n = n + 1;
                }
            } while (!possiblyPrime);
            primeIndex = primeIndex + 1;
            primes[primeIndex] = candicate;
        }
        return primes;
    }
}

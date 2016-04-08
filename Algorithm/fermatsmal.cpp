#include <cstdio>
#include <iostream>
using namespace std;

const int primen = 2;
const int primes[] = {2, 3};

long long multmod(long long x, long long y, long long mod) {
    long long res = 0;
    for(; y; y >>= 1, x = (x + x) % mod)
        if(y & 1) res = (res + x) % mod;
    return res;
}

long long powermod(long long x, long long y, long long mod) {
    long long res = 1;
    for(; y; y >>= 1, x = multmod(x, x, mod))
        if(y & 1) res = multmod(res, x, mod);
    return res;
}

bool MillerRabin(long long n) {
    if(n <= 1) return 0;
    if(n == 2) return 1;
    if(~n & 1) return 0;

    for(register int i = 0; i < primen; ++i) {
        if(n == primes[i]) return 1;
        if(powermod(primes[i], n - 1, n) != 1) return 0;
    }

    return 1;
}

long long x;

void input() {
    cin >> x;
}

void solve() {
    puts(MillerRabin(x) ? "Yes" : "No");
}

int main() {
    int t;
    scanf("%d", &t);
    while(t--) {
        input();
        solve();
    }
    return 0;
}

/*

Miller-Rabin(n):
	If (n <= 2) Then
		If (n == 2) Then
			Return True
		End If
		Return False
	End If
	
	If (n mod 2 == 0) Then
		// n为非2的偶数，直接返回合数
		Return False
	End If
	
	// 我们先找到的最小的a^u，再逐步扩大到a^(n-1)
	
	u = n - 1; // u表示指数
	while (u % 2 == 0) 
		u = u / 2
	End While // 提取因子2
	
	For i = 1 .. S // S为设定的测试次数
		a = rand_Number(2, n - 1) // 随机获取一个2~n-1的数a
		x = a^u % n
		While (u < n) 
			// 依次次检查每一个相邻的 a^u, a^2u, a^4u, ... a^(2^k*u)是否满足二次探测定理
			y = x^2 % n 
			If (y == 1 and x != 1 and x != n - 1)	// 二次探测定理
				// 若y = x^2 ≡ 1(mod n)
				// 但是 x != 1 且 x != n-1
				Return False
			End If
			x = y
			u = u * 2 
		End While
		If (x != 1) Then	// Fermat测试
			Return False
		End If
	End For
	Return True



*/
#include<iostream>
#include<string>
#include<string.h>
#include<stdio.h>
#include<vector>
#include<algorithm>
using namespace std;

//树结构，数据结构
struct TrieNode{
	int count;
	vector<TrieNode*> child;
	//构造函数，这个()为空表示为默认的构造函数
	TrieNode() :count(0), child(26,NULL){};
	TrieNode(int x) :count(x), child(26,NULL){};
};

//插入单词，建树
void InsertWord(char *a, int pos, TrieNode *p)
{
	if (a[pos] == 0) return;//已经遍历完毕,初始化的时候为0
	else
	{
		//如果不存在，则新建儿子节点
		if (p->child[a[pos] - 'a'] == NULL)
			p->child[a[pos] - 'a'] = new TrieNode();
		p->child[a[pos] - 'a']->count++;
		InsertWord(a, pos + 1, p->child[a[pos] - 'a']);
	}
}

//深度搜索
void dfs(int &ans, TrieNode *root)
{
	//如果计数小于5，则不再遍历其儿子节点
	//题目要求的：集合中任意前缀对应的单词数量小于等于5
	if (root->count <= 5)
		ans++;
	else
	{
		//循环遍历儿子节点
		for (int i = 0; i < 26; i++)
		{
			if (root->child[i])
			{
				dfs(ans, root->child[i]);
			}
		}
	}
}

/*
函数名  ：main
函数功能：主函数
*/
int main()
{
	int n;
	//这里这是新建一个节点，该节点下面没有任何子孙节点
	TrieNode *root = new TrieNode;
	root->count = 9999999;
	char *word = new char[1000001];
	memset(word, 0, 1000001);
	cin >> n;

	//输入单词
	for (int i = 0; i < n; i++)
	{
		memset(word, 0, 1000001);
		scanf("%s", word);
		InsertWord(word, 0, root);
	}

	//进行深度搜索
	int ans = 0;
	dfs(ans, root);
	cout << ans << endl;

	return  0;
}

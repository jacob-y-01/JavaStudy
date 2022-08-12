#include<stdio.h>
#include<stdlib.h>
#include<iostream>
#include<conio.h>
#include<Windows.h>
#include<graphics.h>
int foodx = 780, foody = 100;					//食物节点
int direction = 1;							//蛇当前朝向
int snack_die = 0;
int score = 0;								//记分
using namespace std;
class snack
{
public:
	snack()
	{
		next = NULL;
	}

	int x;
	int y;
	int type;
	IMAGE picture;
	snack* next;
};

void drawAlpha(IMAGE* picture, int  picture_x, int picture_y);
//结束显示
void over()
{
	IMAGE p1, p2;
	loadimage(&p2, _T("MM.png"));
	loadimage(&p1, _T("over.png"));
	drawAlpha(&p1, 100, 200);
	drawAlpha(&p2, 1000, 100);
}

//头插法
snack* add_node2(snack* head, int x, int y)
{
	snack* new_node = new snack;
	new_node->x = x, new_node->y = y;
	new_node->next = head;
	return new_node;
}

//尾部删除
void delet_node(snack* head)
{
	snack* p;
	while (head->next->next != NULL)
	{
		head = head->next;
	}
	p = head->next;
	head->next = NULL;
	delete p;

}

//判断按键
void key_judge()
{
	if (GetAsyncKeyState(VK_ESCAPE) != 0)
	{
		direction = 0;									//退出
	}
	else if (GetAsyncKeyState(VK_UP) != 0 && direction != 3)
	{
		direction = 4;
	}
	else if (GetAsyncKeyState(VK_LEFT) != 0 && direction != 1)
	{
		direction = 2;
	}
	else if (GetAsyncKeyState(VK_RIGHT) != 0 && direction != 2)
	{
		direction = 1;
	}
	else if (GetAsyncKeyState(VK_DOWN) != 0 && direction != 4)
	{
		direction = 3;
	}
	else
	{
		return;
	}
}

//初始化
void start_snack(snack* head)
{
	head->x = 740; head->y = 100;
	snack* a1, * a2, * a3, * a4, * a5;
	a1 = new snack; a2 = new snack; a3 = new snack; a4 = new snack; a5 = new snack;
	a1->x = 720; a1->y = 100;
	a2->x = 700; a2->y = 100;
	a3->x = 680; a3->y = 100;
	a4->x = 660; a4->y = 100;
	a5->x = 640; a5->y = 100;
	head->next = a1; a1->next = a2; a2->next = a3; a3->next = a4; a4->next = a5;
}
//1920,1080
//全屏
void FullScreen(HWND hwnd)
{
	int w = GetSystemMetrics(SM_CXSCREEN);
	int h = GetSystemMetrics(SM_CYSCREEN);
	SetWindowLongPtr(hwnd, GWL_STYLE, WS_VISIBLE | WS_POPUP);
	SetWindowPos(hwnd, NULL, 0, 0, w, h, SWP_FRAMECHANGED);
}
//画图
void drawAlpha(IMAGE* picture, int  picture_x, int picture_y)
{
	DWORD* dst = GetImageBuffer();
	DWORD* draw = GetImageBuffer();
	DWORD* src = GetImageBuffer(picture);
	int picture_width = picture->getwidth();
	int picture_height = picture->getheight();
	int graphWidth = getwidth();
	int graphHeight = getheight();
	int dstX = 0;


	for (int iy = 0; iy < picture_height; iy++)
	{
		for (int ix = 0; ix < picture_width; ix++)
		{
			int srcX = ix + iy * picture_width;
			int sa = ((src[srcX] & 0xff000000) >> 24);
			int sr = ((src[srcX] & 0xff0000) >> 16);
			int sg = ((src[srcX] & 0xff00) >> 8);
			int sb = src[srcX] & 0xff;
			if (ix >= 0 && ix <= graphWidth && iy >= 0 && iy <= graphHeight && dstX <= graphWidth * graphHeight)
			{
				dstX = (ix + picture_x) + (iy + picture_y) * graphWidth;
				int dr = ((dst[dstX] & 0xff0000) >> 16);
				int dg = ((dst[dstX] & 0xff00) >> 8);
				int db = dst[dstX] & 0xff;
				draw[dstX] = ((sr * sa / 255 + dr * (255 - sa) / 255) << 16) 
					| ((sg * sa / 255 + dg * (255 - sa) / 255) << 8)        
					| (sb * sa / 255 + db * (255 - sa) / 255);         
			}
		}
	}
}

//设置背景图片
void set_background(IMAGE picture)
{
	putimage(0, 0, &picture);
}

//死亡机制
void die(snack* head)
{
	int headx, heady;
	headx = head->x; heady = head->y;
	if (head->x > 1920 || head->y > 1080 || head->y < 0 || head->x < 0)		//撞墙
	{
		snack_die = 1;
	}
	while (head->next != NULL)
	{
		head = head->next;

		if (head->x == headx && head->y == heady)								//咬自己
		{
			snack_die = 1;
		}

	}
}

//判断是否吃到食物
int eat(snack* head)
{
	if (head->x == foodx && head->y == foody)
	{
		foodx = 0; foody = 0;
		score++;
		return 1;
	}
	else
	{
		return 0;
	}
}



//生成食物
void rand_food()
{
	foodx = rand() % 1000;
	foody = rand() % 800;
	foodx = foodx - (foodx % 20);
	foody = foody - (foody % 20);
	foodx = foodx + 20; foody = foody + 20;
}
void food(snack* head)			//不可与蛇重叠
{
	snack* p1;
	p1 = head;					//将该指针零时存储
	rand_food();
flag:
	while (head->next != NULL)
	{
		head = head->next;
		if (head->x == foodx && head->y == foody)			//如果发现重复，重造，并且goto出循环
		{
			rand_food();
			head = p1;
			goto flag;
		}
	}
}
//输出蛇身体
void print(snack* head)
{
	while (head != NULL)
	{
		printf("%d\t%d\n", head->x, head->y);
		head = head->next;
	}
}

//蛇值改动   蛇节点间隔大小
snack* move_snack(snack* head, int eat)
{
	switch (direction)
	{
	case 0:cout << "结束" << endl; break;
	case 1:head = add_node2(head, head->x + 20, head->y); if (eat == 0) { delet_node(head); }break;
	case 2: head = add_node2(head, head->x - 20, head->y); if (eat == 0) { delet_node(head); }break;
	case 3: head = add_node2(head, head->x, head->y + 20); if (eat == 0) { delet_node(head); }break;
	case 4: head = add_node2(head, head->x, head->y - 20); if (eat == 0) { delet_node(head); }break;
	}
	return head;
}
//根据链表画蛇
void draw_snack(snack* head)
{

	while (head != NULL)
	{
		fillrectangle(head->x, head->y, head->x + 20, head->y + 20);
		head = head->next;
	}
	
}

int main()
{


	IMAGE bk;
	// 加载背景图片
	loadimage(&bk, _T("bk.png"));
	snack* head;
	head = new snack;

	start_snack(head);

	HWND hwnd = initgraph(1920, 1080);				//窗体
	setorigin(0, 0);
	FullScreen(hwnd);
	Sleep(10000);
	setbkcolor(RED);
	setfillcolor(GREEN);
	setcolor(RED);

	cleardevice();



	while (true)				//蛇体
	{
		int eat1 = 0;
		die(head);
		if (snack_die == 1)
		{

			MessageBox(hwnd, TEXT("YOU DIE!"), 0, 0);
			cleardevice();
			set_background(bk);
			over();
			Sleep(5000);

			closegraph();
			PostMessage(hwnd, WM_DESTROY, 0, 0);
		}
		Sleep(100);
		if (_kbhit())										//如果按下了键盘
		{
			key_judge();
		}
		cleardevice();
		if (eat(head) == 1)
		{
			eat1 = 1;
			food(head);
		}
		BeginBatchDraw();
		set_background(bk);
		draw_snack(head);
		fillrectangle(foodx, foody, foodx + 20, foody + 20);	//填充食物
		head = move_snack(head, eat1);
		FlushBatchDraw();
	}


	//HWND hwnd = initgraph(1920, 1080);

	/*setbkcolor(BLUE);
	cleardevice();
	setcolor(RED);

	rectangle(100, 100, 300, 300);
	Sleep(1000);
	FullScreen(hwnd);

	setbkcolor(RED);
	cleardevice();
	setcolor(BLUE);
	rectangle(100, 100, 300, 300);
	int i = 100;
	IMAGE p1;
	loadimage(&p1, _T("2.png"));
	while (i--)
	{
		Sleep(1000);
		cleardevice();
		drawAlpha(&p1, i, i);
	}
	IMAGE p1,p2;
	FullScreen(hwnd);
	loadimage(&p1, _T("background.jpg"));
	set_background(p1);

	loadimage(&p2, _T("2.png"));
	int a=100, b=0, c=0;
	while (a--)
	{
		Sleep(1000);
		set_background(p1);
		drawAlpha(&p2, b++, c++);
	}
	*/


}


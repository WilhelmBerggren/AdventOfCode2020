using System;
using System.IO;
using System.Linq;

var data = File.ReadAllText(Environment.CurrentDirectory + "/../input5.txt").Split("\n");

var ids = data.Select(item => {
    var row = Convert.ToInt32(item.Substring(0, 7).Replace('F', '0').Replace('B', '1'), 2);
    var col = Convert.ToInt32(item.Substring(7, 3).Replace('L', '0').Replace('R', '1'), 2);
    return row * 8 + col;
}).ToArray();

System.Console.WriteLine(ids.Max());

Array.Sort(ids);

var min = ids.First();
for (int i = 1; i < ids.Length; i++)
{
    if (ids[i] != (ids[i-1] + 1)) {
        System.Console.WriteLine(ids[i] - 1);
    }
}
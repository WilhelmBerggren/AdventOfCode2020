using System;
using System.IO;
using System.Linq;

var data = File.ReadAllText(Environment.CurrentDirectory + "/../input1.txt");

var nums = data.Split(Environment.NewLine).Where(s => s.Trim() != "").Select(s => Int32.Parse(s));

var results = 
    from a in nums
    from b in nums
    from c in nums
    where (a + b + c == 2020)
    select (a, b, c);

var ans = results.First();
Console.WriteLine(ans);
Console.WriteLine(ans.Item1 * ans.Item2 * ans.Item3);


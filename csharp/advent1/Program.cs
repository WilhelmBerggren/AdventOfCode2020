using System;
using System.IO;
using System.Linq;

var data = File.ReadAllText(Environment.CurrentDirectory + "/../../inputs/input1.txt");
var nums = data.Split(Environment.NewLine).Select(s => Int32.Parse(s));

var results = 
    from a in nums
    from b in nums
    from c in nums
    where (a + b + c == 2020)
    select a * b * c;

Console.WriteLine(results.First());
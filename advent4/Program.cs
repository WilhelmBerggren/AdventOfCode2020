using System;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;

var data = File.ReadAllText(Environment.CurrentDirectory + "/../input4.txt");

var items = data.Split("\n\n");
Func<string, string[]> Itemize = item => new Regex(@"\s").Split(item);
foreach (var item in items)
{
    var itemized = Itemize(item);
    var parts = itemized.First().Split(":");
    System.Console.WriteLine($"{parts[0]}: {parts[1]}");
}
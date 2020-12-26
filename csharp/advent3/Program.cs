using System;
using System.IO;
using System.Linq;

var data = File.ReadAllText(Environment.CurrentDirectory + "/../../inputs/input3.txt");

var rows = data
    .Split(Environment.NewLine)
    .Select(row => row.ToCharArray())
    .ToArray();

var width = rows[0].Length;
var height = rows.Length;

var pairs = new (int dx, int dy)[] { (1, 1), (3, 1), (5, 1), (7, 1), (1, 2) };

var ans = 
    from pair in pairs
    from row in rows.Select((row, index) => new {row, index})
    let x = row.index * pair.dx % width
    let y = row.index * pair.dy
    where y <= width
    where rows[y][x] == '#'
    group true by pair into hits
    select hits.Count();

var sum = ans.Select(a => (long) a).Aggregate((acc, i) => acc * i);
System.Console.WriteLine(sum);
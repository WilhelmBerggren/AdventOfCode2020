using System;
using System.IO;
using System.Linq;

var groups = File
    .ReadAllText(Environment.CurrentDirectory + "/../../inputs/input6.txt")
    .Split(Environment.NewLine + Environment.NewLine)
    .Select(group => group.Split(Environment.NewLine)
        .Select(s => s
            .Select(c => c)));

var part1 = groups
    .Sum(group => group
        .SelectMany(i => i)
        .Distinct()
        .Count());

var part2 = groups
    .Sum(group => group
        .Aggregate((acc, i) => acc.Intersect(i))
        .Count());

System.Console.WriteLine(part1);
System.Console.WriteLine(part2);
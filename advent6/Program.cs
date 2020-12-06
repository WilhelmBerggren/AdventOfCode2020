using System;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;

var groups = File
    .ReadAllText(Environment.CurrentDirectory + "/../input6.txt")
    .Split(Environment.NewLine + Environment.NewLine)
    .Select(group => group.Split(Environment.NewLine));

var part1 = groups
    .Select(group => group
        .SelectMany(i => i)
        .Distinct()
        .Count())
    .Sum();

var part2 = groups
    .Select(group => group
        .SelectMany(i => i)
        .Distinct()
        .Where(q => group
            .All(c => c
                .Contains(q)))
        .Count())
    .Sum();

System.Console.WriteLine(part1);
System.Console.WriteLine(part2);
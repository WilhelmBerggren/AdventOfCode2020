using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

var data = File
    .ReadAllText(Environment.CurrentDirectory + "/../input7.txt")
    .Split("\n");

var dict = data.Aggregate(
    new Dictionary<string, IEnumerable<string>>(),
    (acc, item) => {
        var parts = item.Split(" bags contain ");
        var key = parts[0];
        var keyParts = parts[0].Split(" ");
        var valueParts = parts[1].Split(", ");

        if (parts[1] == "no other bags.") {
            acc.Add(key, new List<string>());
        }
        else {
            acc.Add(key, valueParts.Select(value => {
                    var p = value.Split(" ");
                    var count = Int32.Parse(p[0]);
                    return $"{p[1]} {p[2]}";
            }));
        }
        return acc;
    });

System.Console.WriteLine(dict.Count());

bool Expand(string name) => dict[name].Contains("shiny gold") ? true : dict[name].Any(s => Expand(s));

var part1 = dict.Sum(i => Expand(i.Key) ? 1 : 0);

System.Console.WriteLine(part1);
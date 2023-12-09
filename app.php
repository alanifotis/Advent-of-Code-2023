<?php

memory_reset_peak_usage();
$start_time = microtime(true);

$_fp = fopen( $argv[1] ?? "input1", "r");
$part1 = $part2 = 0;

while (!feof($_fp))
{
    $line = trim(fgets($_fp));
    foreach ([&$part1, &$part2] as &$part)
    {
        if (preg_match('/(?:(\d).*)?(\d)/', $line, $m))
        {
            array_shift($m);
            if ($m[0] == "") $m[0] = $m[1];
            $part += ((int)($m[0]) * 10) + (int)$m[1];
        }
        // fix line for part2...
        $line = preg_replace
        (
            array("/one/","/two/","/three/","/four/","/five/","/six/","/seven/","/eight/","/nine/"),
            array("o1e","t2o","t3e","f4r","f5e","s6x","s7n","e8t","n9e"),
            $line
        );
    }
    unset($part);
}
fclose($_fp);

echo "part 1: {$part1}\n";
echo "part 2: {$part2}\n";

echo "Execution time: ".round(microtime(true) - $start_time, 4)." seconds\n";
echo "   Peak memory: ".round(memory_get_peak_usage()/pow(2, 20), 4), " MiB\n\n";



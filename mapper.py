#!/usr/bin/env python

# word count source: http://www.michael-noll.com/tutorials/writing-an-hadoop-mapreduce-program-in-python/
import sys

# input comes from STDIN (standard input)
for line in sys.stdin:
    # remove leading and trailing whitespace
    line = line.strip()
    # split the line into words
    words = line.split()
    wordcount = len(words)

    # increase counters
    for i in xrange(wordcount):
       for j in xrange(i+1,wordcount):
         for k in xrange(j+1,wordcount):
            s_list = [words[j], words[k]].sort()
            print '<%s,%s,%s>\t%s' % ( words[i], s_list[0], s_list[1], 1)
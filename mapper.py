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

    print words
    # increase counters
    if wordcount >= 3:
        for i in xrange(wordcount):
            for j in xrange(i+1,wordcount):
                for k in xrange(j+1,wordcount):
                    slist = [words[j], words[k]].sort()
                    print slist
                    print '<%s,%s,%s>\t%s' % ( words[i], slist[0], slist[1], 1)
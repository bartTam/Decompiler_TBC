
// Used for loading dynamic libraries
#include <dlfcn.h>

#include <argp.h>

int main(int argc, char *argv[]){


  argp_parse(&parser, argc, argv, 0, 0, 0);

  return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Чтобы изменить порядок сортировки - измените порядок сравнения полей в comp_struct

struct data {
    char name[21];
    char surname[21];
    char midname[21];
    long long number;
};

void swap (struct data *data, long long a, long long b) {
    struct data x;
    x = data[a];
    data[a] = data[b];
    data[b] = x;
}

int comp_string (char *s1, char *s2) {
    if (strcmp(s1, s2) > 0) {
        return 1;
    } else if (strcmp(s1, s2) < 0) {
        return 2;
    } else {
        return 0;
    }
}

int comp_struct (struct data d1, struct data d2) {
    int ind;

    ind = comp_string(d1.surname, d2.surname);
    if (ind != 0) {
        return ind;
    }

    ind = comp_string(d1.name, d2.name);
    if (ind != 0) {
        return ind;
    }

    ind = comp_string(d1.midname, d2.midname);
    if (ind != 0) {
        return ind;
    }

    if (d1.number > d2.number) {
        return 1;
    } else if (d1.number < d2.number){
        return 2;
    }

    return 0;

}

void qs_rec(struct data *data, long long l, long long r){
    while (l < r) {
        struct data m = data[(l + r) / 2];
        long long a = l, b = r;

        while (a <= b) {
            while (comp_struct(data[a], m) == 2) {
                a++;
            }

            while (comp_struct(data[b], m) == 1) {
                b--;
            }

            if (a > b) {
                break;
            }

            swap(data, a++, b--);
        }

        if ((b - l) < (r - a)) {
            if (l < b) {
                qs_rec(data, l, b);
            }

            l = a;
        } else {
            if (a < r) {
                qs_rec(data, a, r);
            }

            r = b;
        }
    }
}

int main(int argc, char* argv[]) {
    if (argc != 3) {
        printf("%s", "Specify only the file name, the name of the input and output files as arguments");

        return 1;
    }

    FILE *in1 = fopen(argv[1], "r");
    FILE *out = fopen(argv[2], "w");

    if ((in1 == NULL) || (out == NULL)) {
        printf("%s", "The file cannot be opened");

        return 1;
    }

    long long data_size = 0;
    while (!feof(in1)) {
        struct data x;

        if ((fscanf(in1, "%s %s %s %lld", x.surname, x.name, x.midname, &x.number)) == 4) {
            data_size++;
        } else {
            break;
        }
    }

    fclose(in1);

    FILE *in = fopen(argv[1], "r");
    if (in == NULL) {
        printf("%s", "The file cannot be opened");

        return 1;
    }

    struct data *data = malloc(data_size * sizeof(struct data));
    if (data == NULL) {
        printf("%s", "Memory allocation error");

        fclose(in);
        fclose(out);
        
        return 2;
    }

    for (long long i = 0; i < data_size; i++) {
        fscanf(in, "%s %s %s %lld", data[i].surname, data[i].name, data[i].midname, &data[i].number);
    }

    fclose(in);

    qs_rec(data, 0, data_size - 1);

    for (int i = 0; i < data_size; i++) {
        fprintf(out, "%s %s %s %lld\n", data[i].surname, data[i].name, data[i].midname, data[i].number);
    }

    free(data);

    fclose(out);

    return 0;
}

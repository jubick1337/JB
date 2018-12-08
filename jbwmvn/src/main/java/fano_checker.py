right = dict()
left = dict()

def any_fano_fails(l_word, r_word):
    fail = \
        fano_dangerous(left, l_word, r_word) or \
        fano_dangerous(right, r_word, l_word) or \
        fano_dangerous(right, l_word, r_word) or \
        fano_dangerous(left, r_word, l_word)
    return fail

def fano_dangerous(dic, word, meaning):
    w_len = len(word)
    fail = False
    for k in dic:
        if (w_len <= len(k)) and (k[:w_len] == word):
            print('Fano:', '["{}", "{}"] -> ["{}", "{}"]'.format(word, meaning, k, dic[k]))
            fail = True
    return fail

def fan():
    with open('codes.txt', 'r', encoding='UTF-8') as f:
        while True:

            t = [*map(lambda s: s.rstrip('\n'), (f.readline(), f.readline(), f.readline()))]
            print(*t[:2])
            if t[0] == '' or t[1] == '':
                break
            if not any_fano_fails(t[0], t[1]):
                right[t[0]], left[t[1]] = t[1], t[0]

if __name__ == '__main__':
    fan()
    print(len(right), len(left))
